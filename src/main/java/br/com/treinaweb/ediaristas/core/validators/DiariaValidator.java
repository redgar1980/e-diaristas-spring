package br.com.treinaweb.ediaristas.core.validators;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.exceptions.ValidacaoException;
import br.com.treinaweb.ediaristas.core.models.Diaria;
import br.com.treinaweb.ediaristas.core.services.consultaendereco.adapters.EnderecoService;
import br.com.treinaweb.ediaristas.core.services.consultaendereco.exceptions.EnderecoServiceException;

@Component
public class DiariaValidator {

    @Autowired
    private EnderecoService enderecoService;
    
    public void validar(Diaria diaria) {
        validarHoraTermino(diaria);
    }

    private void validarHoraTermino(Diaria diaria) {
        var dataAtendimento = diaria.getDataAtendimento();
        var tempoAtendimento = diaria.getTempoAtendimento();
        var dataTermino = dataAtendimento.plusHours(tempoAtendimento);
        var dataLimite = dataAtendimento.withHour(22).withMinute(0).withSecond(0);

        if (dataTermino.isAfter(dataLimite)) {
            var mensagem = "não pode ser depois das 22";
            var fieldError = new FieldError(diaria.getClass().getName(), "dataAtendimento", diaria.getDataAtendimento(), false, null, null, mensagem);

            throw new ValidacaoException(mensagem, fieldError);
        }

        validarTempoAtendimento(diaria);
    }

    private void validarTempoAtendimento(Diaria diaria) {
        var tempoAtendimento = diaria.getTempoAtendimento();
        var tempoTotal = calcularTempoTotal(diaria);

        if (tempoAtendimento != tempoTotal) {
            var mensagem = "valores não correspondem";
            var fieldError = new FieldError(diaria.getClass().getName(), "tempoAtendimento", diaria.getTempoAtendimento(), false, null, null, mensagem);

            throw new ValidacaoException(mensagem, fieldError);
        }

        validarPreco(diaria);
    }

    private void validarPreco(Diaria diaria) {
        var preco = diaria.getPreco();
        var valorTotal = calcularValorTotal(diaria);

        if (preco.compareTo(valorTotal) != 0) {
            var mensagem = "valores não correspondem";
            var fieldError = new FieldError(diaria.getClass().getName(), "preco", diaria.getPreco(), false, null, null, mensagem);

            throw new ValidacaoException(mensagem, fieldError);
        }

        validarCep(diaria);
    }

    private void validarCep(Diaria diaria) {
        var cep = diaria.getCep();

        try {
            enderecoService.buscarEnderecoPorCep(cep);
        } catch (EnderecoServiceException exception) {
            var mensagem  = exception.getLocalizedMessage();
            var fieldError = new FieldError(diaria.getClass().getName(), "cep", diaria.getCep(), false, null, null, mensagem);

            throw new ValidacaoException(mensagem, fieldError);
        }

    }

    private BigDecimal calcularValorTotal(Diaria diaria) {
        var servico = diaria.getServico();
        var valorMinimo = servico.getValorMinimo();

        var valorQuarto = calcularValorDoComodo(servico.getValorQuarto(), diaria.getQuantidadeQuartos());
        var valorSala = calcularValorDoComodo(servico.getValorSala(), diaria.getQuantidadeSalas());
        var valorCozinha = calcularValorDoComodo(servico.getValorCozinha(), diaria.getQuantidadeCozinhas());
        var valorBanheiro = calcularValorDoComodo(servico.getValorBanheiro(), diaria.getQuantidadeBanheiros());
        var valorQuintal = calcularValorDoComodo(servico.getValorQuintal(), diaria.getQuantidadeQuintais());
        var valorOutros = calcularValorDoComodo(servico.getValorOutros(), diaria.getQuantidadeOutros());

        var valorTotal = valorQuarto.add(valorSala)
            .add(valorCozinha)
            .add(valorBanheiro)
            .add(valorQuintal)
            .add(valorOutros);
        
        if (valorTotal.compareTo(valorMinimo) < 0) {
            return valorMinimo;
        }
        return valorTotal;
    }

    private BigDecimal calcularValorDoComodo(BigDecimal valorComodo, Integer quantidadeDeComodos) {
        return valorComodo.multiply(new BigDecimal(quantidadeDeComodos));
    }

    private Object calcularTempoTotal(Diaria diaria) {
        var servico = diaria.getServico();

        Integer tempoTotal = 0;
        tempoTotal += diaria.getQuantidadeQuartos() * servico.getHorasQuarto();
        tempoTotal += diaria.getQuantidadeSalas() * servico.getHorasSala();
        tempoTotal += diaria.getQuantidadeCozinhas() * servico.getHorasCozinha();
        tempoTotal += diaria.getQuantidadeBanheiros() * servico.getHorasBanheiro();
        tempoTotal += diaria.getQuantidadeQuintais() * servico.getHorasQuintal();
        tempoTotal += diaria.getQuantidadeOutros() * servico.getHorasOutros();
        return tempoTotal;

    }
}
