package br.com.treinaweb.ediaristas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.core.services.consultacidade.adapters.ConsultaCidadeService;
import br.com.treinaweb.ediaristas.core.services.consultacidade.dtos.CidadeResponse;
import br.com.treinaweb.ediaristas.core.services.consultaendereco.adapters.EnderecoService;
import br.com.treinaweb.ediaristas.core.services.consultaendereco.dtos.EnderecoResponse;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoRestController {

    @Autowired
    private EnderecoService service;

    @Autowired
    private ConsultaCidadeService consultaCidadeService;

    @GetMapping
    public EnderecoResponse buscarEnderecoPorCep(@RequestParam(required = false) String cep) {
        return service.buscarEnderecoPorCep(cep);
    }

    @GetMapping("/codigoIbge/{codigoIbge}")
    public CidadeResponse buscarCidadePorCodigoIbge(@PathVariable String codigoIbge) {
        return consultaCidadeService.buscarCidadePorCodigoIbge(codigoIbge);
    }

}
