package com.posfiap.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NotificationMessageDTO {
    private String pacienteNome;
    private String pacienteTelefone;
    private String medicoNome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    private String agendamentoStatus;
}
