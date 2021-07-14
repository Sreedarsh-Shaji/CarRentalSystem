package com.fariz.carrental.dao;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Office {

    @Getter @Setter
    @Id
    private int officeId;


    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Agency", referencedColumnName = "agencyId")
    private Agency agency;

    @Getter @Setter
    private String officeAddress;

    @Getter @Setter
    private String officePhone;

    @Getter @Setter
    private String officeAlternatePhone;

    @Getter @Setter
    private String emailId;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String coordinates;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;


}
