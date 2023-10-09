package br.com.treinaweb.ediaristas.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.ediaristas.api.dtos.responses.CidadeAtendidaResponse;
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

}
