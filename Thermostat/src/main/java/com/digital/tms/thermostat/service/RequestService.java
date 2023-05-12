package com.digital.tms.thermostat.service;

import com.digital.tms.thermostat.config.ThermostatConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RestTemplate restTemplate;
    private final ThermostatConfig config;

    record RequestDTO(String name, String actualTemp) {
    }

    record ThermostatDTO(String name, Double maxTemp, Double actualTemp) {
    }

    record Data(List<ThermostatDTO> data) {
    }

    public record Response(String name, Double maxTemp) {
    }

    public void sendTempData(String name) {
        Random random = new Random();
        double randomNum = (random.nextDouble(config.max() - config.min()));
        var temperature = String.format("%.2f", randomNum);
        System.out.println("temp: "+ temperature);
        var request = new RequestDTO(name, temperature);
        var response = restTemplate.postForObject(config.tempEndpoint(), new HttpEntity<>(request), String.class);
        System.out.println(response);
    }

    public List<Response> getThermostatNames() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var response = restTemplate.getForObject(config.getAllEndpoint(), Data.class);

        return response.data.stream().map(res -> new Response(res.name, res.maxTemp)).toList();
    }


    public void startThermostat() {
        System.out.println("Choose thermostat to run by entering corresponding number");
        Scanner scanner = new Scanner(System.in);
        var response = this.getThermostatNames();
        handleUser(scanner, response);
    }

    private void handleUser(Scanner scanner, List<Response> response) {
        try {
            int i = 0;
            for (Response result : response) {
                var option = String.format("Type %s to select thermostat %s. max temperature %s", i, result.name(), result.maxTemp());
                System.out.println(option);
                i++;
            }
            String num = scanner.nextLine();
            ApplicationState.THERMOSTAT_NAME = response.get(Integer.parseInt(num)).name();
            ApplicationState.CRON_START = true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Input must be within a bounds of an array" +
                    " or there are no thermostats to start. add thermostat before starting");
            handleUser(scanner, response);
        } catch (NumberFormatException e) {
            System.out.println("Input must be a number");
            handleUser(scanner, response);
        }
    }
}
