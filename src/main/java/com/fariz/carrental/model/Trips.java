package com.fariz.carrental.model;

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
    private User UserId;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Vehicle", referencedColumnName = "vehicleId")
    private Vehicle vehicleId;

    @Getter @Setter
    private int pickupOfficeLocation;

    @Getter @Setter
    private int returnOfficeLocation;

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

}
