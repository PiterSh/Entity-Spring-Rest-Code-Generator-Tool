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
@Table(name=Persona.TABLA)
public class Persona {

    public static final String TABLA = "PERSONA";
    public static final String ID_ATRIBUTO = "id";

    public static final String YEARS_ATRIBUTO = "years";
    public static final String COLOR_ATRIBUTO = "color";
    public static final String NAME_ATRIBUTO = "name";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID_ATRIBUTO, nullable = false)
    private Long id;

    @Column(name = YEARS_ATRIBUTO)
    private Integer years;

    @Column(name = COLOR_ATRIBUTO)
    private String color;

    @Column(name = NAME_ATRIBUTO)
    private String name;


}
