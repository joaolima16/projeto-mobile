package com.zecocode.mobile.domain.medico;

import java.util.List;

public record MedicoDTO(
                String nome,
                String crm,
                String especialidade,
                String telefone,
                String email,
                String senha,
                List Atendimento) {
}