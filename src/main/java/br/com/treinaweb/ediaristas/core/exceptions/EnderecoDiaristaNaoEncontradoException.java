package br.com.treinaweb.ediaristas.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class EnderecoDiaristaNaoEncontradoException extends EntityNotFoundException {

    public EnderecoDiaristaNaoEncontradoException(String message) {
        super(message);
    }
}
