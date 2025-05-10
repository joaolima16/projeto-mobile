package com.zecocode.mobile.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zecocode.mobile.domain.paciente.Paciente;
import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public Paciente findByEmail(String email);
}
