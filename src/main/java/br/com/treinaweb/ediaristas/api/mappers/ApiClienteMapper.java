package br.com.treinaweb.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.api.dtos.responses.ClienteResponse;
import br.com.treinaweb.ediaristas.core.models.Usuario;

@Mapper(componentModel = "spring")
public interface ApiClienteMapper {
    
    ApiClienteMapper INSTANCE = Mappers.getMapper(ApiClienteMapper.class);

    @Mapping(target = "tipoUsuario", source = "tipoUsuario.id")
    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    ClienteResponse tResponse(Usuario model);
}
