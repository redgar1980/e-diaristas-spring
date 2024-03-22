package br.com.treinaweb.ediaristas.core.services.gatewaypagamento.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.models.Pagamento;
import br.com.treinaweb.ediaristas.core.services.gatewaypagamento.adapters.GatewayPagamentoService;

@Service
public class PagarMeService implements GatewayPagamentoService {

    private static final String BASE_URL = "https://api.pagar.me/1";

    private final RestTemplate clienteHttp = new RestTemplate();

    @Value("${br.com.treinaweb.ediaristas.pagarme.apiKey}")
    private String apiKey;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Pagamento pagar(Diaria diaria, String cardHash) {
        return null;
    }

}
