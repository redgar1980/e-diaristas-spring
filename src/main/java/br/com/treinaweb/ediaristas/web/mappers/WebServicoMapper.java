package br.com.treinaweb.ediaristas.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.core.models.Servico;
import br.com.treinaweb.ediaristas.web.dtos.ServicoForm;

@Mapper(componentModel = "spring")
public interface WebServicoMapper {

    WebServicoMapper INSTANCE = Mappers.getMapper(WebServicoMapper.class);

    //Converte um servico da camada DTO para Model
    //Alterado para usar o MapperStruct automatizado
    Servico toModel(ServicoForm form);

    //Converte um servico da camada Model para DTO
    //Alterado para usar o MapperStruct automatizado
    ServicoForm toForm(Servico model);


    
}
