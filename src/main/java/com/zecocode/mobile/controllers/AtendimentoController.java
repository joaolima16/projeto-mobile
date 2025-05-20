package com.zecocode.mobile.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zecocode.mobile.domain.atendimento.Atendimento;
import com.zecocode.mobile.domain.atendimento.AtendimentoResponseDTO;
import com.zecocode.mobile.services.AtendimentoService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/atendimento")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class AtendimentoController {

    @Autowired
    AtendimentoService atendimentoService;

    @GetMapping
    public List<Atendimento> findAllAtendimentos() {
        return atendimentoService.findAllAtendimentos();
    }

    @GetMapping("/{pacienteId}")
    public List<Atendimento> findAtendimentosByPacienteId(@PathVariable Long pacienteId) {
        return atendimentoService.findAtendimentosByPacienteId(pacienteId);
    }

    @PostMapping
    public ResponseEntity createAtendimento(@RequestBody AtendimentoResponseDTO atendimento) {
        atendimentoService.create(atendimento);
        return ResponseEntity.status(HttpStatus.OK).body("Paciente criado da base com sucesso!");
    }

    @PutMapping("update/{id}")
    public ResponseEntity putMethodName(@PathVariable Long id) {
        atendimentoService.upAtendimentoStatus(id, true);
        return ResponseEntity.status(HttpStatus.OK).body("Paciente atualizado da base com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleAtendimento(@PathVariable Long id) {
        atendimentoService.deletarAtendimento(id);
        return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado da baseom sucesso!");
    }

}
