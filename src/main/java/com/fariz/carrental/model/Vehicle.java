package com.fariz.carrental.model;

import lombok.*;

import javax.persistence.*;
import java.security.PrivateKey;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Office", referencedColumnName = "officeId")
    private Office currentOffice;

}
