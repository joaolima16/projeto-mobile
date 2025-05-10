package com.zecocode.mobile.domain.medico;

import java.util.List;

import com.zecocode.mobile.domain.atendimento.Atendimento;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String crm;

    private String especialidade;

    private String telefone;

    private String email;

    @Column(length = 70, nullable = false)
    private String senha;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Atendimento> atendimentos;
}
