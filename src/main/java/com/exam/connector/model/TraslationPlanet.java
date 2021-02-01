package com.exam.connector.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
@Table(name = "translationplanet")
@Getter
@Setter
@Accessors(chain = true)
public class TraslationPlanet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long day;
    private int degrees;
    private double radian_angles;
    private double value_x;
    private double value_y;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id")
    private Planet planet;



}
