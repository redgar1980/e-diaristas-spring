package br.com.treinaweb.ediaristas.api.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.api.controllers.CandidaturaRestController;
import br.com.treinaweb.ediaristas.api.dtos.responses.DiariaResponse;

@Component
public class OportunidadeAssembler implements Assembler<DiariaResponse> {

  @Override
  public void adicionarLinks(DiariaResponse resource) {
    var id = resource.getId();

    var candidatarDiariaLink = linkTo(methodOn(CandidaturaRestController.class).candidatar(id))
        .withRel("candidatar_diaria")
        .withType("POST");

    resource.adicionarLinks(candidatarDiariaLink);
  }

  @Override
  public void adicionarLinks(List<DiariaResponse> collectionResource) {
    collectionResource.stream()
        .forEach(this::adicionarLinks);
  }

}
