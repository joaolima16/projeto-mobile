package com.zecocode.mobile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zecocode.mobile.domain.atendimento.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

}