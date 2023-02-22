package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.LibroDto;
import co.edu.uniquindio.biblioteca.entity.Libro;
import co.edu.uniquindio.biblioteca.servicios.LibroServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libro")
@AllArgsConstructor
public class LibroController {

    private final LibroServicio libroServicio;

    @PostMapping
    public Libro save(@RequestBody Libro libro){
        return libroServicio.save(libro);
    }

    @GetMapping("/{isbn}")
    public LibroDto findById(@PathVariable String isbn){
        return libroServicio.findByIsbn(isbn);
    }

    @GetMapping
    public List<LibroDto> findAll(){
        return libroServicio.findAll();
    }

    @DeleteMapping("/{isbn}")
    public String delete(@PathVariable String isbn){
        libroServicio.delete(isbn);
        return "Se eliminó";
    }

    @PutMapping("/{isbn}")
    public Libro update(@PathVariable String isbn, @RequestBody Libro libro){
        return libroServicio.update(isbn, libro);
    }

}
