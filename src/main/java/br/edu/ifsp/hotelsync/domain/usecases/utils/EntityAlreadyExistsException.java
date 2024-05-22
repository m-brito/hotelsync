package br.edu.ifsp.hotelsync.domain.usecases.utils;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
