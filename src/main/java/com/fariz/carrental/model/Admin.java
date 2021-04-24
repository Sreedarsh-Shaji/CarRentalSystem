package com.fariz.carrental.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    @Column(nullable = false , unique = true)
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
