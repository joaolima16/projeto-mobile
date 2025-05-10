package com.zecocode.mobile.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zecocode.mobile.domain.paciente.PacientLogin;
import com.zecocode.mobile.domain.paciente.Paciente;
import com.zecocode.mobile.domain.paciente.PacienteDTO;
import com.zecocode.mobile.repositories.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder encoder;

    // CADASTRA PACIENTE
    public Paciente createPatient(PacienteDTO paciente) {

        Paciente newPaciente = new Paciente();
        newPaciente.setName(paciente.nome());
        newPaciente.setCpf(paciente.cpf());
        newPaciente.setIdade(paciente.idade());
        newPaciente.setEndereco(paciente.endereco());
        newPaciente.setTelefone(paciente.telefone());
        newPaciente.setEmail(paciente.email());

        String senhaCriptografada = encoder.encode(paciente.senha());
        newPaciente.setSenha(senhaCriptografada);
        return pacienteRepository.save(newPaciente);
    }

    // ENCONTRA TODOS OS PACIENTES
    public List<Paciente> findAllPaciente() {
        return pacienteRepository.findAll();
    }

    // DELETA PACIENTE DA BASE
    public void deletarPaciente(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            pacienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Paciente com ID " + id + " não encontrado.");
        }
    }

    // ENCONTRA PACIENTE POR ID
    public Paciente findPacienteById(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    // Login Usuário
    public PacientLogin pacientLogin(PacientLogin paciente) {
        Paciente pacienteEntcontrado = pacienteRepository.findByEmail(paciente.email());

        if (pacienteEntcontrado != null) {
            boolean validate = encoder.matches(paciente.senha(), pacienteEntcontrado.getSenha());
            if (paciente == null || !validate) {
                return null;
            }
        }
        return paciente;
    }

    // ENCONTRA PACIENTE POR EMAIL
    public Paciente findPacienteByEmail(String email) {
        return pacienteRepository.findByEmail(email);
    }

}
