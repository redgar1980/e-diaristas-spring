package br.com.treinaweb.ediaristas.core.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.treinaweb.ediaristas.core.models.Diaria;

public class DiariaSpecifications {

  public static Specification<Diaria> comNumeroCandidatosIgualA(int numeroCandidatos) {
    return (root, query, criteriaBuilder) -> {
      var quantidadeCandidatos = criteriaBuilder.size(root.get("Diaria_.candidatos"));
      return criteriaBuilder.equal(quantidadeCandidatos, numeroCandidatos);
    };
  }
}
