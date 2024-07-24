package com.anuj.spoorthi.customexceptions;

public class ResourceNotFoundException extends RuntimeException {

  public  ResourceNotFoundException(String message){
        super(message);
    }
}
