package com.exam.connector.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "weather")
@Getter
@Setter
@Accessors(chain = true)
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long day;
    private double distanceSF;
    private double distanceFV;
    private double distanceVB;
    private double distanceBF;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;
    private double sum;
    private String weather;

}
