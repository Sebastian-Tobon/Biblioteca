package co.edu.uniquindio.biblioteca.servicios;

import co.edu.uniquindio.biblioteca.dto.LibroDto;
import co.edu.uniquindio.biblioteca.entity.Libro;
import co.edu.uniquindio.biblioteca.repo.LibroRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.LibroNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibroServicio {

    private final LibroRepo libroRepo;

    //Guardar Libro
    public Libro save(Libro libro){
        return libroRepo.save(libro);
    }

    public LibroDto findByIsbn(String isbn){
        Libro libro = libroRepo.findByIsbn(isbn).orElseThrow( () -> new LibroNoEncontradoException("El Libro no existe") );

        return convertirLibro(libro);
    }

    private LibroDto convertirLibro(Libro libro){
        return new LibroDto(libro.getIsbn(), libro.getNombre(), libro.getGenero(), libro.getFechaPublicacion());
    }

    public void delete(String isbnLibro){
        libroRepo.findById(isbnLibro).orElseThrow( () -> new LibroNoEncontradoException("El Libro no existe") );
        libroRepo.deleteById(isbnLibro);
    }

    public Libro update(String isbnLibro, Libro libroNuevo){
        libroRepo.findById(isbnLibro).orElseThrow( () -> new LibroNoEncontradoException("El Libro no existe") );
        //clienteNuevo.setCodigo(codigoCliente);
        return libroRepo.save(libroNuevo);
    }

    public List<LibroDto> findAll(){
        return libroRepo.findAll()
                .stream()
                .map(c -> convertirLibro(c))
                .collect(Collectors.toList());
    }
}
