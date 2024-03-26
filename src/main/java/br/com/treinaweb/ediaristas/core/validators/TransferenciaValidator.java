package br.com.treinaweb.ediaristas.core.validators;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.exceptions.ValidacaoException;
import br.com.treinaweb.ediaristas.core.models.Diaria;

@Component
public class TransferenciaValidator {

    public void validar(Diaria diaria) {
        validarStatus(diaria);
    }

    private void validarStatus(Diaria diaria) {
        var statusValidos = List.of(DiariaStatus.CONCLUIDO, DiariaStatus.AVALIADO);
        if (!statusValidos.contains(diaria.getStatus())) {
            var mensagem = "Status inválido para realizar transferência";
            var fieldError = new FieldError(diaria.getClass().getName(), "status", diaria.getStatus(), false, null,
                    null, mensagem);
            throw new ValidacaoException(mensagem, fieldError);
        }
    }

}
