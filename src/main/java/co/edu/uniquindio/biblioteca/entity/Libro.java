package co.edu.uniquindio.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Slf4j
public class Libro {

    @Id
    private String isbn;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String genero;
    @Column(nullable = false)
    private int unidades;
    @Column(nullable = false)
    @ManyToMany
    private List<Autor> autor;

    private LocalDate fechaPublicacion;

}
