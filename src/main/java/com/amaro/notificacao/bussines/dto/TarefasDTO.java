package com.amaro.notificacao.bussines.dto;

import com.amaro.notificacao.bussines.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefasDTO {
    private String id;
    private String nome;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private Status statusTarefa;
}
