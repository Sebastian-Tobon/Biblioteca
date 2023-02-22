package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.AutorDto;
import co.edu.uniquindio.biblioteca.dto.LibroDto;
import co.edu.uniquindio.biblioteca.entity.Autor;
import co.edu.uniquindio.biblioteca.repo.AutorRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.LibroNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AutorServicio {

    private final AutorRepo autorRepo;

    public Autor save(Autor autor){
        return autorRepo.save(autor);
    }

    public AutorDto findByid(long id){
        Autor autor = autorRepo.findById(id).orElseThrow( () -> new LibroNoEncontradoException("El Libro no existe") );

        return convertirAutor(autor);
    }

    private AutorDto convertirAutor(Autor autor){
        return new AutorDto(autor.getId(), autor.getNombre());
    }

    public void delete(long idLibro){
        autorRepo.findById(idLibro).orElseThrow( () -> new LibroNoEncontradoException("El Libro no existe") );
        autorRepo.deleteById(idLibro);
    }

    public  Autor update(long idAutor, Autor autorNuevo){
        autorRepo.findById(idAutor).orElseThrow( () -> new LibroNoEncontradoException("El Libro no existe") );
        return autorRepo.save(autorNuevo);
    }

    public List<AutorDto> findAll(){
        return autorRepo.findAll()
                .stream()
                .map(c -> convertirAutor(c))
                .collect(Collectors.toList());
    }
}
