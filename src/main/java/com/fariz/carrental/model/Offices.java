package com.fariz.carrental.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Offices {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int officeId;

    @Getter @Setter
    private String officeAddress;

    @Getter @Setter
    private String officePhone;

    @Getter @Setter
    private String officeAlternatePhone;

    @Getter @Setter
    private String emailId;

    @Getter @Setter
    private String agency;

    @Getter @Setter
    private String coordinates;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;

}
