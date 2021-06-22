package com.example.pragma.servicioclientes.infrastructure.adapters.imagenes;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.example.pragma.servicioclientes.application.config.messages.constants.ApiConfigData.MICRO_IMAGENES_URI;

@Component
@RequiredArgsConstructor
public class ImagenesAdapter {

    private final RestTemplate restTemplate;
    private final MessageSource messageSource;

    public ImagenDto consultarImagen(String imagenId) {
        ImagenDto imagen = restTemplate.getForObject(
                messageSource.getMessage(MICRO_IMAGENES_URI, null, null) + imagenId,
                ImagenDto.class);
        return imagen;
    }

    public Map<String, ImagenDto> consultarImagenes(List<String> imagenIds) {
        ImagenDto[] imagenes = restTemplate.getForObject(
                messageSource.getMessage(MICRO_IMAGENES_URI, null, null),
                ImagenDto[].class,
                imagenIds);

        Map<String, ImagenDto> imagenesAlmacenadas = new TreeMap<>();

        for (ImagenDto imagenActual : imagenes) {
            imagenesAlmacenadas.put(imagenActual.getId(), imagenActual);
        }
        return imagenesAlmacenadas;
    }

    public Map<String, ImagenDto> consultarImagenes() {
        ImagenDto[] imagenes = restTemplate.getForObject(
                messageSource.getMessage(MICRO_IMAGENES_URI, null, null),
                ImagenDto[].class);
        Map<String, ImagenDto> imagenesAlmacenadas = new TreeMap<>();
        for (ImagenDto imagenActual : imagenes) {
            imagenesAlmacenadas.put(imagenActual.getId(), imagenActual);
        }
        return imagenesAlmacenadas;
    }


    public ImagenDto guardarImagen(ImagenDto imagenDto) {
        ImagenDto ImagenCreada = restTemplate.postForObject(
                messageSource.getMessage(MICRO_IMAGENES_URI, null, null),
                imagenDto,
                ImagenDto.class);
        return ImagenCreada;
    }

    public void actualizarImagen(ImagenDto imagenDto) {
        restTemplate.put(
                messageSource.getMessage(MICRO_IMAGENES_URI, null, null) + imagenDto.getId(),
                imagenDto,
                ImagenDto.class);
    }

    public void eliminarImagen(String imagenId) {
        restTemplate.delete(messageSource.getMessage(MICRO_IMAGENES_URI, null, null) + imagenId);
    }

}
