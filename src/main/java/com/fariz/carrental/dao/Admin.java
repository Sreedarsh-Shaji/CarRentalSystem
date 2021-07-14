package com.fariz.carrental.dao;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int adminId;

    @Getter
    @Setter
    private String adminName;

    @Getter
    @Setter
    private String adminEmail;

    @Getter
    @Setter
    private String adminPassword;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;

    @Getter
    @Setter
    Date lastLogin;

}
