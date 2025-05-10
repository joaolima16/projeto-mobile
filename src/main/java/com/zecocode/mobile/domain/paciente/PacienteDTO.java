package com.zecocode.mobile.domain.paciente;

public record PacienteDTO(
        String nome,
        String cpf,
        String email,
        int idade,
        String endereco,
        String telefone,
        String senha) {

}