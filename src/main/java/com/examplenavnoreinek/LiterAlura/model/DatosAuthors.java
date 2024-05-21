package com.examplenavnoreinek.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public class DatosAuthors {
    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private Integer nacimiento;

    @JsonAlias("death_year")
    private Integer fallecimiento;

    public String getNombre() {
        return nombre;
    }
    public Integer getNacimiento() {
        return nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }
}
