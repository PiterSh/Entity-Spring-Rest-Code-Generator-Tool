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
@Table(name=Dios.TABLA)
public class Dios {

    public static final String TABLA = "DIOS";
    public static final String ID_ATRIBUTO = "id";
    public static final String ESTUDIANTE_ID = "estudiante_id";
    public static final String PERSONA_ID = "persona_id";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID_ATRIBUTO, nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ESTUDIANTE_ID, referencedColumnName = Estudiante.ID_ATRIBUTO)
    private Estudiante estudiante;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = PERSONA_ID, referencedColumnName = Persona.ID_ATRIBUTO)
    private Persona persona;
}
