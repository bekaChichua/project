package com.digital.tms.repo;

import com.digital.tms.model.entity.ThermostatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThermostatRepo extends JpaRepository<ThermostatEntity, Long> {
    List<ThermostatEntity> getThermostatEntitiesByUserEmail(String email);

    Optional<ThermostatEntity> findByUser_EmailAndName(String email, String name);
    void deleteByUser_EmailAndName(String email, String name);

    Optional<ThermostatEntity> getThermostatEntitiesByName(String name);
}
