package com.zecocode.mobile.controllers;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zecocode.mobile.domain.paciente.Paciente;
import com.zecocode.mobile.domain.paciente.PacienteDTO;
import com.zecocode.mobile.services.PacienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getPaciente() {
        return pacienteService.findAllPaciente();
    }

    @PostMapping
    public ResponseEntity createPaciente(@RequestBody PacienteDTO paciente) {
        // TODO: process POST request
        this.pacienteService.createPatient(paciente);
        return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED)
                .body("Paciente cadastrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePaciente(@PathVariable Long id) {

        this.pacienteService.deletarPaciente(id);

        return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado da base com sucesso");
    }

}
