package br.com.treinaweb.ediaristas.core.services.consultacidade.adapters;

import br.com.treinaweb.ediaristas.core.services.consultacidade.dtos.CidadeResponse;
import br.com.treinaweb.ediaristas.core.services.consultacidade.exceptions.ConsultaCidadeException;

public interface ConsultaCidadeService {

    CidadeResponse buscarCidadePorCodigoIbge(String codigoIbge) throws ConsultaCidadeException;
}
