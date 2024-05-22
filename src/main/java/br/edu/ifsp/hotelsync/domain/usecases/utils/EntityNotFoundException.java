package br.edu.ifsp.hotelsync.domain.usecases.utils;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
