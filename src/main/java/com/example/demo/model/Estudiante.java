package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name=Estudiante.TABLA)
public class Estudiante {

    public static final String TABLA = "ESTUDIANTE";
    public static final String ID_ATRIBUTO = "id";
    public static final String STUDENT_NAME_ATRIBUTO = "STUDENT_NAME";
    public static final String BIRTH_DATE_ATRIBUTO = "birthDate";
    public static final String GENERO_ATRIBUTO = "genero";

    public static final String AGE_ATRIBUTO = "edad";



    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name=ID_ATRIBUTO)
    private Long id;

    @Column(name=STUDENT_NAME_ATRIBUTO, length=50, nullable=false)
    private String name;

    @Transient
    @Column(name=AGE_ATRIBUTO)
    private Integer age;

    @Column(name = BIRTH_DATE_ATRIBUTO, columnDefinition = "DATE")
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = GENERO_ATRIBUTO)
    private Genero genero;

}
