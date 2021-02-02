package com.exam.connector.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "translationplanet")
@Getter
@Setter
@Accessors(chain = true)
public class TranslationPlanet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long day;
    private int degrees;
    private double radian_angles;
    private double value_x;
    private double value_y;
    private int planet_id;


}
