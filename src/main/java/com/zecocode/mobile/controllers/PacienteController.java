package com.zecocode.mobile.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zecocode.mobile.domain.paciente.PacientLogin;
import com.zecocode.mobile.domain.paciente.Paciente;
import com.zecocode.mobile.domain.paciente.PacienteDTO;
import com.zecocode.mobile.services.PacienteService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/paciente")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getPaciente() {
        return pacienteService.findAllPaciente();
    }

    @GetMapping("/{email}")
    public Paciente getPacienteByEmail(@PathVariable String email) {
        return pacienteService.findPacienteByEmail(email);
    }

    @PostMapping
    public ResponseEntity<Object> createPaciente(@RequestBody PacienteDTO paciente) {

        if (pacienteService.findPacienteByEmail(paciente.email()) == null) {
            this.pacienteService.createPatient(paciente);
            return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED)
                    .body("Paciente cadastrado com sucesso!");
        } else {
            return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST)
                    .body("Paciente já existe na base dedos!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePaciente(@PathVariable Long id) {
        this.pacienteService.deletarPaciente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado da base com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody PacientLogin paciente) {

        if (pacienteService.pacientLogin(paciente) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Usuário não existe na base de dados, ou credenciais estão erradas!");
        } else if (pacienteService.findPacienteByEmail(paciente.email()) == null) {
            return ResponseEntity.status(HttpStatus.LOCKED).body("Usuário está inativo na base de dados!");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login realizado!");
    }

}
