package com.example.pragma.servicioclientes.infrastructure.adapters;

import com.example.pragma.servicioclientes.application.mappers.MapeadorDtoModeloImagenes;
import com.example.pragma.servicioclientes.application.mappers.MapeadorPersistenciaModelo;
import com.example.pragma.servicioclientes.domain.gateway.EnlaceModeloInterface;
import com.example.pragma.servicioclientes.domain.model.Cliente;
import com.example.pragma.servicioclientes.domain.model.Imagen;
import com.example.pragma.servicioclientes.infrastructure.adapters.imagenes.ImagenDto;
import com.example.pragma.servicioclientes.infrastructure.adapters.imagenes.ImagenesAdapter;
import com.example.pragma.servicioclientes.infrastructure.adapters.mysql.ClienteEntidad;
import com.example.pragma.servicioclientes.infrastructure.adapters.mysql.ClienteMysql;
import com.example.pragma.servicioclientes.infrastructure.adapters.mysql.ClientePk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteRepositorio implements EnlaceModeloInterface {

    private final ClienteMysql clienteMysql;
    private final MapeadorPersistenciaModelo mapeador;
    private final MapeadorDtoModeloImagenes mapeadorImagenes;
    private final ImagenesAdapter imagenesAdapter;

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        Imagen imagen = cliente.getFoto();
        if(imagen!= null && imagen.getContenido() != null && !imagen.getContenido().isEmpty()){
            ImagenDto nuevaImagen = ImagenDto.builder()
                    .id(cliente.getTipoDeDocumento().concat(cliente.getNumeroDeIdentificacion()))
                    .contenido(cliente.getFoto().getContenido())
                    .build();
            imagen = mapeadorImagenes.AModelo(imagenesAdapter.guardarImagen(nuevaImagen));
            cliente.setFoto(imagen);
        }

        ClienteEntidad clienteAGuardar = mapeador.aPersistencia(cliente);
        ClienteEntidad clienteBD = clienteMysql.save(clienteAGuardar);
        clienteBD.setImagen(imagen);
        return mapeador.aModelo(clienteBD);
    }

    @Override
    public Cliente consultarCliente(String tipoDocumento, String numeroIdentificacion) {
        ClientePk idCliente = ClientePk.builder()
                .tipoDocumento(tipoDocumento)
                .numeroDeIdentificacion(numeroIdentificacion)
                .build();

        Optional<ClienteEntidad> clienteBD = clienteMysql.findById(idCliente);
        Cliente clienteEncontrado =  null;
        if(clienteBD.isPresent()){
            ImagenDto imagen = imagenesAdapter.consultarImagen(clienteBD.get().getIdFoto());
            clienteEncontrado = mapeador.aModelo(clienteBD.get());
            clienteEncontrado.setFoto(mapeadorImagenes.AModelo(imagen));

        }
        return clienteEncontrado;
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        ClientePk idCliente = ClientePk.builder()
                .tipoDocumento(cliente.getTipoDeDocumento())
                .numeroDeIdentificacion(cliente.getNumeroDeIdentificacion())
                .build();

        Optional<ClienteEntidad> clienteExistente = clienteMysql.findById(idCliente);
        if(clienteExistente.isPresent()){
            Imagen imagenActualizada = actualizarImagen(
                    clienteExistente.get().getIdFoto(),
                    cliente.getFoto().getContenido()
            );
            cliente.setFoto(imagenActualizada);
            clienteMysql.save(mapeador.aPersistencia(cliente));
        }
        return cliente;
    }

    @Override
    public List<Cliente> listarClientes() {
        List<ClienteEntidad> clientes = clienteMysql.findAll();
        return actualizarImagenesModelo(clientes);
    }

    @Override
    public void eliminarCliente(String tipoDocumento, String numeroIdentificacion) {
        ClientePk idCliente = ClientePk.builder()
                .tipoDocumento(tipoDocumento)
                .numeroDeIdentificacion(numeroIdentificacion)
                .build();
        Optional<ClienteEntidad> clienteExistente = clienteMysql.findById(idCliente);
        imagenesAdapter.eliminarImagen(clienteExistente.get().getIdFoto());
        clienteMysql.delete(clienteExistente.get());
    }

    @Override
    public List<Cliente> listarClientesPorEdad(Integer edad) {
        List<ClienteEntidad> clientes =  clienteMysql.findByEdadGreaterThanEqual(edad);
        List<String> identificadoresImagenes =  new ArrayList<>();

        for (ClienteEntidad clienteActual: clientes) {
            identificadoresImagenes.add(clienteActual.getIdFoto());
        }

        Map<String, ImagenDto>  list = imagenesAdapter.consultarImagenes(identificadoresImagenes);

        return actualizarImagenesModelo(clientes);
    }

    private Imagen actualizarImagen(String id, String contenido){
        Imagen imagenActualizada = Imagen.builder()
                .id(id)
                .contenido(contenido)
                .build();
        imagenesAdapter.actualizarImagen(mapeadorImagenes.ADto(imagenActualizada));
        return imagenActualizada;
    }

    private List<Cliente> actualizarImagenesModelo(List<ClienteEntidad> clientesEndidad){
        List<Cliente> clientesModelo = mapeador.aModelos(clientesEndidad);
        for (Cliente clienteActual: clientesModelo) {
            Imagen foto = clienteActual.getFoto();
            if(foto != null && foto.getId() != null && !foto.getId().isEmpty()){
                ImagenDto imagenBD = imagenesAdapter.consultarImagen(clienteActual.getFoto().getId());
                Imagen imagen = mapeadorImagenes.AModelo(imagenBD);
                clienteActual.setFoto(imagen);
            }
        }
        return clientesModelo;
    }
}
