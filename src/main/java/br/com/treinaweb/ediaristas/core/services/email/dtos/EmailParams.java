package br.com.treinaweb.ediaristas.core.services.email.dtos;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailParams {

    private String destinatario;
    private String assunto;
    private String template;
    private Map<String, Object> props;
}
