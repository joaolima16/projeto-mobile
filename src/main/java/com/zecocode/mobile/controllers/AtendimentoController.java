package com.zecocode.mobile.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zecocode.mobile.domain.atendimento.AtendimentoResponseDTO;
import com.zecocode.mobile.services.AtendimentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    AtendimentoService atendimentoService;

    @GetMapping
    public ResponseEntity findAllAtendimentos() {

        atendimentoService.findAllAtendimentos();

        return ResponseEntity.status(HttpStatus.OK).body("Lista de todos os atendimentos");
    }

    @PostMapping
    public ResponseEntity createAtendimento(@RequestBody AtendimentoResponseDTO atendimento) {
        atendimentoService.create(atendimento);
        return ResponseEntity.status(HttpStatus.OK).body("Paciente criardo da base com sucesso!");
    }

}
