package br.com.brunovaz.gestao_vagas.modules.candidate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunovaz.gestao_vagas.modules.company.entities.JobEntity;

public interface ApplyJobRepository extends JpaRepository<JobEntity, UUID>{
    
}
