package co.edu.uniquindio.biblioteca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@NoArgsConstructor
@Data
public class Autor {

    @Id
    private long id;

    private String nombre;

}
