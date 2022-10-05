package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    private static final String FIELD_ID = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = FIELD_ID, nullable = false)
    private Long id;

    private Integer years;
    private String color;
    private String name;


}
