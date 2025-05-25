package com.novamentis.webnova.repository;
import com.novamentis.webnova.model.Alumno;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlumnoRepository extends MongoRepository<Alumno, String> {

}
