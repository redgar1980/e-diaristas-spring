package br.com.treinaweb.ediaristas.core.services.gatewaypagamento.adapters;

import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Pagamento;

public interface GatewayPagamentoService {

    Pagamento pagar(Diaria diaria, String cardHash);

    Pagamento realizarEstornoTotal(Diaria diaria);
}
