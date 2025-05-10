package com.zecocode.mobile.domain.atendimento;

import java.time.LocalDate;
import java.time.LocalTime;

import com.zecocode.mobile.domain.medico.Medico;
import com.zecocode.mobile.domain.paciente.Paciente;

public record AtendimentoResponseDTO(
                String especialidade,
                LocalDate dataAtendimento,
                LocalTime horaAtendimento,
                String sala,
                Medico medico,
                Paciente paciente) {
}