package com.digital.tms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThermostatEntity extends AbstractPersistable<Long> {
    @Column(unique = true, nullable = false)
    private String name;
    private Double maxTemp;
    private Double actualTemp;
    private Boolean isCritical;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonIgnore
    private UserEntity user;

    @JsonIgnore
    public void setCritical() {
        this.isCritical = actualTemp > maxTemp;
    }
}
