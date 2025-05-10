package com.zecocode.mobile.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zecocode.mobile.domain.medico.Medico;
import com.zecocode.mobile.domain.medico.MedicoDTO;
import com.zecocode.mobile.repositories.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico findMedicoById(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    public List<Medico> findAllMedicos() {
        return medicoRepository.findAll();
    }

    public Medico createPatient(MedicoDTO medicoDto) {

        Medico medico = new Medico();
        medico.setNome(medicoDto.nome());
        medico.setCrm(medicoDto.crm());
        medico.setEspecialidade(medicoDto.especialidade());
        medico.setTelefone(medicoDto.telefone());

        return medicoRepository.save(medico);
    }

}
