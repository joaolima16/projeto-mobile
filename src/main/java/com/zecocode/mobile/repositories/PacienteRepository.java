package com.zecocode.mobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import com.zecocode.mobile.domain.paciente.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
