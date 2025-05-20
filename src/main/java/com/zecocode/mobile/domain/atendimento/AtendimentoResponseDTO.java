package com.zecocode.mobile.domain.atendimento;

import java.time.LocalDate;
import java.time.LocalTime;

public record AtendimentoResponseDTO(
        String especialidade,
        LocalDate dataAtendimento,
        LocalTime horaAtendimento,
        String sala,
        boolean status,
        Long medico,
        Long paciente) {
}