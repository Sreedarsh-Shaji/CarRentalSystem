package com.fariz.carrental.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Agency {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int agencyId;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    Date lastLogin;

    @Getter @Setter
    private boolean approved;
}
