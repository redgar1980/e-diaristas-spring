package br.com.treinaweb.ediaristas.api.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.api.dtos.requests.DiariaCancelamentoRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.repositories.DiariaRepository;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.adapters.GatewayPagamentoService;
import br.com.treinaweb.ediaristas.core.validators.DiariaCancelamentoValidador;

@Service
public class ApiDiariaCancelamentoService {

    @Autowired
    private DiariaRepository diariaRepository;

    @Autowired
    private DiariaCancelamentoValidador validador;

    @Autowired
    private GatewayPagamentoService gatewayPagamentoService;

    public MensagemResponse cancelar(Long diariaId, DiariaCancelamentoRequest request) {
        var diaria = buscarDiariaPorId(diariaId);
        validador.validar(diaria);

        diaria.setStatus(DiariaStatus.CANCELADO);
        diaria.setMotivoCancelamento(request.getMotivoCancelamento());
        diariaRepository.save(diaria);

        if (hasPenalizacao(diaria)) {
            aplicarPenalizacao(diaria);
        } else {
            gatewayPagamentoService.realizarEstornoTotal(diaria);
        }

        return new MensagemResponse("A diária foi cancelada com sucesso!");
    }

    private void aplicarPenalizacao(Diaria diaria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'aplicarPenalizacao'");
    }

    private Diaria buscarDiariaPorId(Long diariaId) {
        var mensagem = String.format("Diária com id %d não encontrada", diariaId);
        return diariaRepository.findById(diariaId)
                .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }

    private boolean hasPenalizacao(Diaria diaria) {
        var hoje = LocalDateTime.now();
        return ChronoUnit.HOURS.between(hoje, diaria.getDataAtendimento()) < 24;
    }
}
