package com.novamentis.webnova.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.novamentis.webnova.model.mongo.Alumno;

public interface AlumnoRepository extends MongoRepository<Alumno, String> {

}
