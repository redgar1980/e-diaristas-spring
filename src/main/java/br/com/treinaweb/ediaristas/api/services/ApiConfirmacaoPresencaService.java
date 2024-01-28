package br.com.treinaweb.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.validators.ConfirmacaoPresencaValidator;

@Service
public class ApiConfirmacaoPresencaService {

    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private ConfirmacaoPresencaValidator validator;

    public MensagemResponse confirmarPresenca(Long id) {
        var diaria = buscarDiariaPorId(id);
        validator.validar(diaria);
        diaria.setStatus(DiariaStatus.CONCLUIDO);
        diariaRepository.save(diaria);
        return new MensagemResponse("Presença confirmada com sucesso!");
    }

    private Diaria buscarDiariaPorId(Long id) {
        var mensagem = String.format("Diária de id %id não encontrada", id);
        return diariaRepository.findById(id)
                .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }
}
