package medtech.com.consumer.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NotificationMessage {

    @NotBlank
    private String pacienteNome;

    @NotBlank
    private String pacienteTelefone;

    @NotBlank
    private String medicoNome;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @NotBlank
    private String agendamentoStatus;
}
