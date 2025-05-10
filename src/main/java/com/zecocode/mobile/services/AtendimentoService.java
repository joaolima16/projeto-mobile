package com.zecocode.mobile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zecocode.mobile.domain.atendimento.Atendimento;
import com.zecocode.mobile.domain.atendimento.AtendimentoResponseDTO;
import com.zecocode.mobile.repositories.AtendimentoRepository;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    public Atendimento create(AtendimentoResponseDTO atendimento) {
        try {
            Atendimento newAtendimento = new Atendimento();
            newAtendimento.setEspecialidade(atendimento.especialidade());
            newAtendimento.setDataAtendimento(atendimento.dataAtendimento());
            newAtendimento.setHoraAtendimento(atendimento.horaAtendimento());
            newAtendimento.setMedico(atendimento.medico());
            newAtendimento.setPaciente(atendimento.paciente());

            return atendimentoRepository.save(newAtendimento);
        } catch (Exception e) {
            throw new RuntimeException("Error to create student");
        }
    }

}
