package com.examplenavnoreinek.LiterAlura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public class DatosBook {

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<DatosAuthors> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private int descargas;


    public String getTitulo() {
        return titulo;
    }
    public List<DatosAuthors> getAutores() {
        return autores;
    }
    public List<String> getIdiomas() {
        return idiomas;
    }
    public int getDescargas() {
        return descargas;
    }
}
