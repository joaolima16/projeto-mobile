package com.zecocode.mobile.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zecocode.mobile.domain.medico.Medico;
import com.zecocode.mobile.domain.medico.MedicoDTO;
import com.zecocode.mobile.services.MedicoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> getPaciente() {
        return medicoService.findAllMedicos();
    }

    @PostMapping
    public ResponseEntity createMedico(@RequestBody MedicoDTO medico) {

        if (medicoService.findMedicoByEmail(medico.email()) == null) {
            medicoService.createMedico(medico);
            return ResponseEntity.status(HttpStatus.OK).body("Paciente criardo da base com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente já existe na base de dados!");
        }

    }

}
