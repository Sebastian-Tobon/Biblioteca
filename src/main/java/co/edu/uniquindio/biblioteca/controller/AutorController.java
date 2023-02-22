package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.AutorDto;
import co.edu.uniquindio.biblioteca.entity.Autor;
import co.edu.uniquindio.biblioteca.servicios.AutorServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autor")
@AllArgsConstructor
public class AutorController {

    private final AutorServicio autorServicio;

    @PostMapping
    public Autor save(@RequestBody Autor autor){
        return autorServicio.save(autor);
    }

    @GetMapping("/{id}")
    public AutorDto findById(@PathVariable long id){
        return autorServicio.findByid(id);
    }

    @GetMapping
    public List<AutorDto> findAll(){
        return autorServicio.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        autorServicio.delete(id);
        return "Se eliminó Autor";
    }

    @PutMapping("/{id}")
    public Autor update(@PathVariable long id, @RequestBody Autor autor){
        return autorServicio.update(id, autor);
    }

}
