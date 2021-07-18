package com.fariz.carrental.dao;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Trips {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tripId;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User", referencedColumnName = "userId")
    private User User;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Agency", referencedColumnName = "agencyId")
    private Agency agency;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Vehicle", referencedColumnName = "vehicleId")
    private Vehicle vehicle;

    @Getter @Setter
    private String pickupOfficeLocation;

    @Getter @Setter
    private String returnOfficeLocation;

    @Getter @Setter
    private Date startDate;

    @Getter @Setter
    private Date endDate;

    @Getter @Setter
    private int rating;

    @Getter @Setter
    private String review;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;

    @Getter @Setter
    private boolean active =  true;

}
