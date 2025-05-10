package com.zecocode.mobile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zecocode.mobile.domain.paciente.Paciente;
import com.zecocode.mobile.domain.paciente.PacienteDTO;
import com.zecocode.mobile.repositories.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public Paciente createPatient(PacienteDTO paciente) {

        Paciente newPaciente = new Paciente();
        newPaciente.setName(paciente.nome());
        newPaciente.setCpf(paciente.cpf());
        newPaciente.setIdade(paciente.idade());
        newPaciente.setEndereco(paciente.endereco());
        newPaciente.setTelefone(paciente.telefone());

        return pacienteRepository.save(newPaciente);
    }

}
