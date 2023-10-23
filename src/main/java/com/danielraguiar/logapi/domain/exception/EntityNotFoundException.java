package com.danielraguiar.logapi.domain.exception;

public class EntityNotFoundException extends Exceptions{
    private static final long serialVersionUID = 1L;
    public EntityNotFoundException(String message) {
        super(message);
    }
}
