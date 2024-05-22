package com.examplenavnoreinek.LiterAlura.principal;
import com.examplenavnoreinek.LiterAlura.model.Datos;
import com.examplenavnoreinek.LiterAlura.model.DatosBook;
import com.examplenavnoreinek.LiterAlura.service.ConsumoAPI;
import com.examplenavnoreinek.LiterAlura.service.ConvierteDatos;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosBook> librosBuscados;


    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.muestraElMenu();
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro 
                    2 - Buscar libros por autor
                    3 - Top 10 de libros más descargados
                    4 - Listar todos los libros
                                  
                    0 - Salir
                    """;
            System.out.println(menu);

            try {
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1:
                        buscarLibro();
                        break;
                    case 2:
                        buscarLibrosPorAutor();
                        break;
                    case 3:
                        mostrarTop10Libros();
                        break;
                    case 4:
                        listarTodosLosLibros();
                        break;

                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }
    private void buscarLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();

        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, Datos.class);

        if (datos != null && datos.getResultado() != null && !datos.getResultado().isEmpty()) {
            var resultado = datos.getResultado().get(0);
            var autor = resultado.getAutores().isEmpty() ? "Desconocido" : resultado.getAutores().get(0).getNombre();

            System.out.println("Título: " + resultado.getTitulo() +
                    "\nAutor: " + autor +
                    "\nIdioma: " + resultado.getIdiomas() +
                    "\nDescargas: " + resultado.getDescargas());
        } else {
            System.out.println("No se encontraron libros con ese título.");
        }
    }
    private void buscarLibrosPorAutor() {
        System.out.println("Escribe el nombre del autor que deseas buscar");
        var nombreAutor = teclado.nextLine();

        var json = consumoApi.obtenerDatos(URL_BASE + nombreAutor.replace(" ", "+") + "&authors=");
        var datos = conversor.obtenerDatos(json, Datos.class);

        if (datos != null && datos.getResultado() != null && !datos.getResultado().isEmpty()) {
            var resultado = datos.getResultado().get(0);
            System.out.println("Resultados para el autor:");

            var autor = resultado.getAutores().isEmpty() ? "Desconocido" : resultado.getAutores().get(0).getNombre();
            var nacimiento = resultado.getAutores().isEmpty() ? "Desconocido" : resultado.getAutores().get(0).getNacimiento();
            var fallecimiento = resultado.getAutores().isEmpty() ? "Desconocido" : resultado.getAutores().get(0).getFallecimiento();

            System.out.println(
                            "\nAutor: " + autor +
                            "\nNacimiento: " + nacimiento +
                            "\nFallecimiento: " + fallecimiento);
        } else {
            System.out.println("No se encontraron libros para el autor " + nombreAutor + ".");
        }
    }


    private void mostrarTop10Libros() {
        var json = consumoApi.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);

        if (datos != null && datos.getResultado() != null && !datos.getResultado().isEmpty()) {
            var libros = datos.getResultado();
            libros.sort(Comparator.comparingInt(DatosBook::getDescargas).reversed());

            System.out.println("Top 10 libros más descargados:");
            for (int i = 0; i < Math.min(10, libros.size()); i++) {
                var libro = libros.get(i);
                var idioma = libro.getIdiomas().isEmpty() ? "Desconocido" : libro.getIdiomas().get(0);
                var autor = libro.getAutores().isEmpty() ? "Desconocido" : libro.getAutores().get(0).getNombre();
                var descargas = libro.getDescargas();

                System.out.println((i + 1) + ". Título: " + libro.getTitulo() +
                                "\nAutor: " + autor +
                                "\nIdioma: " + idioma +
                                "\nDescargas: " + descargas);
            }
        } else {
            System.out.println("No se encontraron libros.");
        }
    }
    private void listarTodosLosLibros() {
        var json = consumoApi.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);

        if (datos != null && datos.getResultado() != null && !datos.getResultado().isEmpty()) {
            System.out.println("Listado de todos los libros:");

            for (var libro : datos.getResultado()) {
                var idioma = libro.getIdiomas().isEmpty() ? "Desconocido" : libro.getIdiomas().get(0);
                var autor = libro.getAutores().isEmpty() ? "Desconocido" : libro.getAutores().get(0).getNombre();

                System.out.println("  - Título: " + libro.getTitulo() +
                            "\nAutor: " + autor +
                            "\nIdioma: " + idioma);
            }
        } else {
            System.out.println("No se encontraron libros.");
        }
    }
}





















/*
    private void listarLibrosPorIdioma() {
        System.out.println("Escribe el idioma que deseas buscar");
        var idioma = teclado.nextLine();

        var json = consumoApi.obtenerDatos(URL_BASE + "&language=" + idioma);
        var datos = conversor.obtenerDatos(json, Datos.class);

        if (datos != null && datos.getResultado() != null && !datos.getResultado().isEmpty()) {
            var resultado = datos.getResultado().get(0); // Get the first result
            System.out.println("Resultados para el idioma " + idioma + ":");

            var idiomaLibro = resultado.getIdiomas().isEmpty() ? "Desconocido" : resultado.getIdiomas().get(0);
            var autor = resultado.getAutores().isEmpty() ? "Desconocido" : resultado.getAutores().get(0).getNombre();
            var descargas = resultado.getDescargas();

            System.out.println("  - Título: " + resultado.getTitulo());
            System.out.println("    Autor: " + autor);
            System.out.println("    Idioma: " + idiomaLibro);
            System.out.println("    Descargas: " + descargas);
        } else {
            System.out.println("No se encontraron libros en el idioma " + idioma + ".");
        }
    }*/ 