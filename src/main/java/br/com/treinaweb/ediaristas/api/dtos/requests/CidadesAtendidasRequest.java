package br.com.treinaweb.ediaristas.api.dtos.requests;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadesAtendidasRequest {

    @NotNull
    @NotEmpty
    private List<CidadeAtendidaRequest> cidades;

}
