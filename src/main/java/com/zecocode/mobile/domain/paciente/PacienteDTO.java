package com.zecocode.mobile.domain.paciente;

public record PacienteDTO(
        String nome,
        String cpf,
        int idade,
        String endereco,
        String telefone) {

}