package com.zecocode.mobile.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zecocode.mobile.domain.medico.Medico;
import com.zecocode.mobile.domain.medico.MedicoDTO;
import com.zecocode.mobile.domain.medico.MedicoLogin;
import com.zecocode.mobile.services.MedicoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/medico")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> getPaciente() {
        return medicoService.findAllMedicos();
    }

    @GetMapping("/{email}")
    public Medico getMedicoByEmail(@PathVariable String email) {
        return medicoService.findMedicoByEmail(email);
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

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody MedicoLogin medico) {

        if (medicoService.medicoLogin(medico) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não existe na base de dados, ou credenciais estão erradas!");
        } else if (medicoService.findMedicoByEmail(medico.email()) == null) {
            return ResponseEntity.status(HttpStatus.LOCKED).body("Usuário está inativo na base de dados!");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login realizado!");
    }

}
