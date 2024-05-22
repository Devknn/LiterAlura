package com.examplenavnoreinek.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Datos {

    @JsonAlias("results")
    private List<DatosBook> resultado;

    public List<DatosBook> getResultado() {
        return resultado;
    }

}
