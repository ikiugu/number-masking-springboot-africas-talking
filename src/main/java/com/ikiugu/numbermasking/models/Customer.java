package com.ikiugu.numbermasking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @Column(unique = true)
    private String phoneNumber;
    private boolean contacted = false;
    private boolean calling = false;
    private String comment;

    @ManyToOne
    @JsonIgnore
    public Agent agent;


}
