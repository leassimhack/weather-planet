package com.exam.connector.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "validation")
@Getter
@Setter
@Accessors(chain = true)
public class ValidationJob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String flag;


}
