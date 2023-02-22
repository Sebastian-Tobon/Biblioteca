package co.edu.uniquindio.biblioteca.servicios;


import co.edu.uniquindio.biblioteca.dto.ClienteDto;
import co.edu.uniquindio.biblioteca.dto.ClientePost;
import co.edu.uniquindio.biblioteca.entity.Cliente;
import co.edu.uniquindio.biblioteca.repo.ClienteRepo;
import co.edu.uniquindio.biblioteca.servicios.excepciones.ClienteNoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServicio {

    private final ClienteRepo clienteRepo;

    public ClienteDto save(ClientePost cliente){
        return convertirCliente( clienteRepo.save( convertirCliente(cliente) ) );
    }


    public ClienteDto findById(Long codigoCliente){
        Cliente cliente = obtenerCliente(codigoCliente);
        return convertirCliente(cliente);
    }

    public void delete(long codigoCliente){
        obtenerCliente(codigoCliente);
        clienteRepo.deleteById(codigoCliente);
    }

    public ClienteDto update(long codigoCliente, ClientePost clienteNuevo){
        obtenerCliente(codigoCliente);

        Cliente nuevo = convertirCliente(clienteNuevo);
        nuevo.setCodigo(codigoCliente);
        return convertirCliente( clienteRepo.save(nuevo) );
    }

    public List<ClienteDto> findAll(){
        return clienteRepo.findAll()
                .stream()
                .map(c -> convertirCliente(c))
                .collect(Collectors.toList());
    }

    private Cliente obtenerCliente(Long codigoCliente){
        return clienteRepo.findById(codigoCliente).orElseThrow( () -> new ClienteNoEncontradoException("El cliente no existe") );
    }

    private ClienteDto convertirCliente(Cliente cliente){
        return new ClienteDto(cliente.getCodigo(), cliente.getNombre(), cliente.getEmail(), cliente.getTelefono());
    }

    private Cliente convertirCliente(ClientePost cliente){
        return Cliente.builder()
                .nombre(cliente.nombre())
                .email(cliente.email())
                .telefono(cliente.telefono())
                .password(cliente.password()).build();
    }

}
