package com.novamentis.webnova.repository;

import com.novamentis.webnova.model.mongo.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentario, String> {
}
