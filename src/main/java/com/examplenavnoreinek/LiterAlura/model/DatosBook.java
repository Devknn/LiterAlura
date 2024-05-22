package com.examplenavnoreinek.LiterAlura.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import java.util.List;

@Entity
@Table(name= "book")

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<DatosAuthors> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private int descargas;


    public Long getId() {
        return Id;
    }
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
