package com.example.pragma.servicioimagenes.infrastructure.adapters.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("imagenes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ImagenPersistenteMongo {
    @Id
    private String id;
    private String contenido;
}
