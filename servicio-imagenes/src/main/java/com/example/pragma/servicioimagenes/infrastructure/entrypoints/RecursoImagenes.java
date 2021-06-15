package com.example.pragma.servicioimagenes.infrastructure.entrypoints;

import com.example.pragma.servicioimagenes.application.mappers.MapeadorPeticionModelo;
import com.example.pragma.servicioimagenes.application.mappers.MapeadorRespuestaModelo;
import com.example.pragma.servicioimagenes.domain.model.Imagen;
import com.example.pragma.servicioimagenes.domain.usecase.GestionarImagenesInterface;
import com.example.pragma.servicioimagenes.infrastructure.entrypoints.dtos.ImagenPeticion;
import com.example.pragma.servicioimagenes.infrastructure.entrypoints.dtos.ImagenRespuesta;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("imagenes")
public class RecursoImagenes {

    @Autowired
    private GestionarImagenesInterface gestionarImagenes;

    @Autowired
    private MapeadorRespuestaModelo mapeadorRespuesta;

    @Autowired
    private MapeadorPeticionModelo mapeadorPeticion;

    @ApiOperation("Guardar imagen")
    @ApiResponses({
            @ApiResponse(code = 201, message = "la imagen fue guardada")
    })
    @PostMapping
    public ResponseEntity<ImagenRespuesta> guardarImagen(
            @ApiParam(value = "imagen en formato Json", required = true)
            @RequestBody ImagenPeticion imagenPeticion){

        Imagen imagen = mapeadorPeticion.aModelo(imagenPeticion);
        Imagen imagenCreada = gestionarImagenes.guardarImagen(imagen);
        ImagenRespuesta respuesta = mapeadorRespuesta.aRespuesta(imagenCreada);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @ApiOperation("consultar una imagen por el identificador")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "la imagen no fue encontrada")
    })
    @GetMapping("/{identificador}")
    public ResponseEntity<ImagenRespuesta> consultarImagen(
            @PathVariable String identificador){

        Imagen imagenEncontrada = gestionarImagenes.consultarImagen(identificador);
        ImagenRespuesta imagenRespuesta = mapeadorRespuesta.aRespuesta(imagenEncontrada);
        if(imagenEncontrada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(imagenRespuesta);
    }

    @ApiOperation("Lista todas las imagenes registradas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 204, message = "No hay contenido para mostrar")
    })
    @GetMapping
    public ResponseEntity<List<ImagenRespuesta>> listarImagenes(){
        List<Imagen> imagenesEncontradas = gestionarImagenes.listarImagenes();
        List<ImagenRespuesta> imagenesRespuesta = mapeadorRespuesta.aRespuestas(imagenesEncontradas);
        if(imagenesRespuesta.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(imagenesRespuesta);
    }

    @ApiOperation("Actualiza la imagen")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "La imagen no esta registrada")
    })
    @PutMapping("/{identificacion}")
    public ResponseEntity<ImagenRespuesta> actualizarImagen(
            @ApiParam(value = "Identificador de la imagen en la BD", required = true)
            @PathVariable String identificacion,
            @ApiParam(value = "imagen en formato Json", required = true)
            @RequestBody ImagenPeticion imagenPeticion){

        Imagen imagen = mapeadorPeticion.aModelo(imagenPeticion);
        Imagen imagenActualizada = gestionarImagenes.actualizarContenido(identificacion,imagen);
        ImagenRespuesta respuesta = mapeadorRespuesta.aRespuesta(imagenActualizada);

        if (imagenActualizada == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    @ApiOperation("Elimina la imagen")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "No se encuentra en la base de datos")
    })
    @DeleteMapping("/{identificacion}")
    public ResponseEntity eliminarImagen(
            @ApiParam(value = "Identificador de la imagen en la BD", required = true)
            @PathVariable String identificacion){

        gestionarImagenes.eliminarImagen(identificacion);
        return ResponseEntity.ok().build();
    }
}
