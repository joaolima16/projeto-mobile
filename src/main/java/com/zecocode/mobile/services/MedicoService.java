package com.zecocode.mobile.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zecocode.mobile.domain.medico.Medico;
import com.zecocode.mobile.domain.medico.MedicoDTO;
import com.zecocode.mobile.domain.medico.MedicoLogin;
import com.zecocode.mobile.domain.paciente.PacientLogin;
import com.zecocode.mobile.domain.paciente.Paciente;
import com.zecocode.mobile.repositories.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Medico findMedicoById(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    public List<Medico> findAllMedicos() {
        return medicoRepository.findAll();
    }

    public Medico createMedico(MedicoDTO medicoDto) {

        Medico medico = new Medico();
        medico.setNome(medicoDto.nome());
        medico.setCrm(medicoDto.crm());
        medico.setEspecialidade(medicoDto.especialidade());
        medico.setTelefone(medicoDto.telefone());

        medico.setEmail(medicoDto.email());

        String senhaCriptografada = encoder.encode(medicoDto.senha());
        medico.setSenha(senhaCriptografada);

        return medicoRepository.save(medico);
    }

    // ENCONTRA PACIENTE POR EMAIL
    public Medico findMedicoByEmail(String email) {
        return medicoRepository.findByEmail(email);
    }

    // Login Medico
    public MedicoLogin medicoLogin(MedicoLogin medico) {
        Medico medicoEncontrado = medicoRepository.findByEmail(medico.email());

        if (medicoEncontrado != null) {
            boolean validate = encoder.matches(medico.senha(), medicoEncontrado.getSenha());
            if (medico == null || !validate) {
                return null;
            }
        }
        return medico;
    }

}
