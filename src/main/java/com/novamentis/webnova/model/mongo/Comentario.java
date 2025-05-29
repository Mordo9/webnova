package com.novamentis.webnova.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comentarios")
public class Comentario {
  @Id
  private String id;

  // Nombres de campos deben coincidir con los atributos 'name' de tu form
  private String name;
  private String email;
  private String phone;
  private String subject;
  private String message;
}
