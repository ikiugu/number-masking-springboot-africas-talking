package com.ikiugu.numbermasking.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "agent")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String phoneNumber;

    @OneToMany(mappedBy = "agent")
    private List<Customer> customers = new ArrayList<>();
}
