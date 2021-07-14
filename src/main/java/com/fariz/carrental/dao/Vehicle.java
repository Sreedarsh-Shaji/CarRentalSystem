package com.fariz.carrental.dao;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Vehicle {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vehicleId;

    @Getter @Setter
    private String carName;

    @Getter @Setter
    private String manufacturer;

    @Getter @Setter
    private String registerNumber;

    @Getter @Setter
    private String chassisNumber;

    @Getter @Setter
    private String engineNumber;

    @Getter @Setter
    private String fuelType;

    @Getter @Setter
    private int kmsOperated;

    @Getter @Setter
    private int hourlyRate;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;

    @Getter @Setter
    @Column(name="status", columnDefinition="varchar(255) default 'false'")
    private String status;

    @Getter @Setter
    private int currentOffice;

}
