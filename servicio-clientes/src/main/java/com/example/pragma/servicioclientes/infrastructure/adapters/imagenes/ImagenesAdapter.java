package com.example.pragma.servicioclientes.infrastructure.adapters.imagenes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.TreeMap;

@Component
@RequiredArgsConstructor
public class ImagenesAdapter {

    private final RestTemplate restTemplate;

    public ImagenDto consultarImagen(String imagenId){
        ImagenDto imagen = restTemplate.getForObject(
                "http://localhost:8081/imagenes/" +  imagenId,
                ImagenDto.class);
        return imagen;
    }

    public Map<String ,ImagenDto> consultarImagenes(){
        ImagenDto[] imagenes = restTemplate.getForObject(
                "http://localhost:8081/imagenes/",
                ImagenDto[].class);
        Map<String,ImagenDto> imagenesAlmacenadas = new TreeMap<>();
        for (ImagenDto imagenActual: imagenes) {
            imagenesAlmacenadas.put(imagenActual.getId(),imagenActual);
        }
        return imagenesAlmacenadas;
    }


    public ImagenDto guardarImagen(ImagenDto imagenDto){
        ImagenDto ImagenCreada = restTemplate.postForObject(
                "http://localhost:8081/imagenes/",
                imagenDto,
                ImagenDto.class);
        return ImagenCreada;
    }

    public void actualizarImagen(ImagenDto imagenDto){
       restTemplate.put(
                "http://localhost:8081/imagenes/" +  imagenDto.getId(),
                imagenDto,
                ImagenDto.class);
    }

    public void eliminarImagen(String imagenId){
         restTemplate.delete("http://localhost:8081/imagenes/" + imagenId);
    }


}
