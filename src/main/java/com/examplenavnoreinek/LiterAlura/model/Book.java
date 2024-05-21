package com.examplenavnoreinek.LiterAlura.model;

public class Book {
    private String titulo;
    private String autor;
    private String idioma;
    private int descargas;

    public Book(String titulo, String autor, String idioma, int descargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + idioma + '\'' +
                ", descargas=" + descargas +
                '}';
    }
}
