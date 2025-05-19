package com.zecocode.mobile.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zecocode.mobile.domain.atendimento.Atendimento;
import com.zecocode.mobile.domain.atendimento.AtendimentoResponseDTO;
import com.zecocode.mobile.domain.medico.Medico;
import com.zecocode.mobile.domain.paciente.Paciente;
import com.zecocode.mobile.repositories.AtendimentoRepository;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    public Atendimento create(AtendimentoResponseDTO atendimento) {
        try {
            Atendimento newAtendimento = new Atendimento();
            newAtendimento.setEspecialidade(atendimento.especialidade());
            newAtendimento.setDataAtendimento(atendimento.dataAtendimento());
            newAtendimento.setHoraAtendimento(atendimento.horaAtendimento());
            newAtendimento.setSala(atendimento.sala());
            newAtendimento.setStatus(atendimento.status());

            Medico medico = medicoService.findMedicoById(atendimento.medico());
            Paciente paciente = pacienteService.findPacienteById(atendimento.paciente());

            newAtendimento.setMedico(medico);
            newAtendimento.setPaciente(paciente);

            return atendimentoRepository.save(newAtendimento);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar novo atendimento");
        }
    }

    public List<Atendimento> findAllAtendimentos() {
        return atendimentoRepository.findAll();
    }

    public List<Atendimento> findAtendimentosByPacienteId(Long pacienteId) {
        return atendimentoRepository.findByPacienteId(pacienteId);
    }

    // DELETA ATENDIMENTO DA BASE
    public void deletarPaciente(Long id) {
        Optional<Atendimento> atendimento = atendimentoRepository.findById(id);
        if (atendimento.isPresent()) {
            atendimentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Paciente com ID " + id + " não encontrado.");
        }
    }

}
