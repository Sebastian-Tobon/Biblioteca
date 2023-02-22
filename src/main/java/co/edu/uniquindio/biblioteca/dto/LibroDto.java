package co.edu.uniquindio.biblioteca.dto;

import java.time.LocalDate;

public record LibroDto(String isbn, String nombre, String genero, LocalDate fechaPublicacion) {

}
