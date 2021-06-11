package com.example.pragma.servicioimagenes.infrastructure.adapters.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImagenMongoDB extends MongoRepository<ImagenPersistenteMongo,String> {
}
