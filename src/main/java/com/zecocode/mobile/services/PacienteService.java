package com.zecocode.mobile.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zecocode.mobile.domain.medico.Medico;
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

    public List<Paciente> findAllPaciente() {
        return pacienteRepository.findAll();
    }

    public void deletarPaciente(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            pacienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Paciente com ID " + id + " não encontrado.");
        }
    }

    public Paciente findPacienteById(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

}
