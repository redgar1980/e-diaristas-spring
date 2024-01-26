package br.com.treinaweb.ediaristas.core.specifications;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import br.com.treinaweb.ediaristas.core.enums.DiariaStatus;
import br.com.treinaweb.ediaristas.core.models.Diaria;

public class DiariaSpecifications {

  public static Specification<Diaria> comNumeroCandidatosIgualA(int numeroCandidatos) {
    return (root, query, criteriaBuilder) -> {
      var quantidadeCandidatos = criteriaBuilder.size(root.get("candidatos"));
      return criteriaBuilder.equal(quantidadeCandidatos, numeroCandidatos);
    };
  }

  public static Specification<Diaria> comNumeroCandidatosMenorQue(int numeroCandidatos) {
    return (root, query, criteriaBuilder) -> {
      var quantidadeCandidatos = criteriaBuilder.size(root.get("candidatos"));
      return criteriaBuilder.lessThan(quantidadeCandidatos, numeroCandidatos);
    };
  }

  public static Specification<Diaria> comNumeroCandidadosMaiorOuIgualA(int numeroCandidatos) {
    return (root, query, criteriaBuilder) -> {
      var quantidadeCandidatos = criteriaBuilder.size(root.get("candidatos"));
      return criteriaBuilder.greaterThanOrEqualTo(quantidadeCandidatos, numeroCandidatos);
    };
  }

  public static Specification<Diaria> isPago() {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get("status"), DiariaStatus.PAGO);
    };
  }

  public static Specification<Diaria> semDiarista() {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.isNull(root.get("diarista"));
    };
  }

  public static Specification<Diaria> comMais24HorasDesdeCriacao() {
    return (root, query, criteriaBuilder) -> {
      return criteriaBuilder.lessThanOrEqualTo(
          root.get("createdAt"),
          LocalDateTime.now().minusHours(24));
    };
  }

}
