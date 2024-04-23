package br.com.treinaweb.ediaristas.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class PasswordResetNaoEncontradoException extends EntityNotFoundException {

    public PasswordResetNaoEncontradoException() {
    }

    public PasswordResetNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
