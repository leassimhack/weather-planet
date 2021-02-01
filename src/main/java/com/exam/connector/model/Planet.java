package com.exam.connector.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@Table(name = "planet")
@Getter
@Setter
@Accessors(chain = true)
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "radius_km")
    private double radiusKM;
    @XmlAttribute(name = "distance_sun_km")
    private double distanceSunKm;
    private int velocity;

}
