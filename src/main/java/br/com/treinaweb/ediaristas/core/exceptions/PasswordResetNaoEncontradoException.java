package br.com.treinaweb.ediaristas.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class PasswordResetNaoEncontradoException extends EntityNotFoundException {

    public PasswordResetNaoEncontradoException() {
    }

    public PasswordResetNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
