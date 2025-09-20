package br.com.brunovaz.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.brunovaz.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.brunovaz.gestao_vagas.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;




@RestController
@RequestMapping ("/candidate")
public class CandidateControllers {

    @Autowired
    private CandidateRepository candidateRepository;
    
    @PostMapping("/")
    public CandidateEntity create( @Valid @RequestBody CandidateEntity candidateEntity){
        return this.candidateRepository.save(candidateEntity);

    }
    
}
