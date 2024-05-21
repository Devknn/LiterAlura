package com.examplenavnoreinek.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Datos {

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private DatosAuthors autor; 

    @JsonAlias("results")
    private List<DatosBook> resultado;

    public List<DatosBook> getResultado() {
        return resultado;
    }

    public void setResultado(List<DatosBook> resultado) {
        this.resultado = resultado;
    }

}
