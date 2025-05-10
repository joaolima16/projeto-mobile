package com.zecocode.mobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zecocode.mobile.domain.medico.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
