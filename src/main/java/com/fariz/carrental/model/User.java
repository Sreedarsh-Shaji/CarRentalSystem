package com.fariz.carrental.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String phoneNumber;

    @Getter @Setter
    private String licenseNumber;

    @Column(name="status", columnDefinition="varchar(255) default 'false'")
    @Getter @Setter
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    Date creationDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    Date lastLogin;

}
