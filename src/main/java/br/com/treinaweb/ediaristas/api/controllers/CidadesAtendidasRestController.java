package br.com.treinaweb.ediaristas.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.requests.CidadesAtendidasRequest;
import br.com.treinaweb.ediaristas.api.dtos.responses.CidadeAtendidaResponse;
import br.com.treinaweb.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.treinaweb.ediaristas.api.services.ApiCidadesAtendidasService;
import br.com.treinaweb.ediaristas.core.permissions.EDiaristasPermissions;

@RestController
@RequestMapping("/api/usuarios/cidades-atendidas")
public class CidadesAtendidasRestController {

    @Autowired
    private ApiCidadesAtendidasService service;

    @EDiaristasPermissions.isDiarista
    @GetMapping
    public List<CidadeAtendidaResponse> listarCidadesAtendidas() {
        return service.listarCidadesAtendidas();
    }

    @EDiaristasPermissions.isDiarista
    @PutMapping
    public MensagemResponse atualizarCidadesAtendidas(@RequestBody @Valid CidadesAtendidasRequest request) {
        return service.atualizarCidadesAtendidas(request);
    }

}
