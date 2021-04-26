package com.compasso.error;


import org.springframework.http.HttpStatus;  
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(Long id) {
    super("Não foi possível encontrar produto " + id);
  }
}