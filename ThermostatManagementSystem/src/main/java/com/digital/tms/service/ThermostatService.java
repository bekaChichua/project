package com.digital.tms.service;

import com.digital.tms.model.dto.ThermostatDTO;
import com.digital.tms.model.entity.ThermostatEntity;
import com.digital.tms.model.exceptions.EmailAlreadyExistsException;
import com.digital.tms.model.exceptions.EntityNotFoundException;
import com.digital.tms.model.exceptions.ThermostatAlreadyExistsException;
import com.digital.tms.repo.ThermostatRepo;
import com.digital.tms.repo.UserEntityRepo;
import com.digital.tms.service.iServicies.IThermostatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class ThermostatService implements IThermostatService {
    private final ThermostatRepo thermostatRepo;
    private final UserEntityRepo userRepo;
    private final ObjectMapper mapper;

    private final Function<ThermostatEntity, ThermostatDTO> converter = entity -> new ThermostatDTO(entity.getName(), entity.getMaxTemp(), entity.getActualTemp());

    @Override
    public List<ThermostatDTO> getAll() {
        return thermostatRepo.findAll()
                .stream()
                .map(converter)
                .toList();
    }

    @Override
    public List<ThermostatDTO> getAll(String email) {
        log.debug("user with email: {} trying to retrieve thermostats", email);
        return thermostatRepo.getThermostatEntitiesByUserEmail(email)
                .stream()
                .map(converter)
                .toList();
    }

    @Override
    public ThermostatDTO getThermostatBy(String email, String name) {
        return thermostatRepo.findByUser_EmailAndName(email, name)
                .map(converter)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void addThermostat(String email, ThermostatDTO thermostat) {
        try {
            var thermostatEntity = mapper.convertValue(thermostat, ThermostatEntity.class);
            thermostatEntity.setUser(userRepo.findByEmail(email).orElseThrow(EntityNotFoundException::new));
            thermostatRepo.save(thermostatEntity);
            log.debug("thermostat with name: {} was added to DB by user with email: {}", thermostat.name(), email);
        } catch ( DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new ThermostatAlreadyExistsException(thermostat.name());
        }
    }

    @Override
    @Transactional
    public void deleteThermostatBy(String email, String name) {
        thermostatRepo.deleteByUser_EmailAndName(email, name);
        log.debug("thermostat with name: {} was deleted by user with email: {}", name, email);
    }

    @Override
    public Boolean updateThermostat(String email, ThermostatDTO thermostat) {
        ThermostatEntity entity = null;
        try {
            entity = thermostatRepo.findByUser_EmailAndName(email, thermostat.name()).orElseThrow(EntityNotFoundException::new);
            BeanUtils.copyProperties(thermostat, entity);
            thermostatRepo.save(entity);
            log.debug("thermostat with name: {} was updated", entity.getName());
            return true;
        } catch (EntityNotFoundException e) {
            log.warn("thermostat doesn't exists to update");
            assert entity != null;
            this.addThermostat(email, thermostat);
            return false;
        }
    }

    @Override
    public Boolean updateThermostat(String name, Double temperature) {
        var entity = thermostatRepo.getThermostatEntitiesByName(name).orElseThrow(EntityNotFoundException::new);
        entity.setActualTemp(temperature);
        entity.setCritical();
        boolean response =  entity.getIsCritical();
        thermostatRepo.save(entity);
        return response;
    }
}
