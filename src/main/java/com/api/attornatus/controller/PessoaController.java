package com.api.attornatus.controller;

import com.api.attornatus.dto.pessoa.DadosCadastroPessoaDto;
import com.api.attornatus.service.pessoa.ServiceCadastrarPessoa;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final ServiceCadastrarPessoa serviceCadastrarPessoa;

    public PessoaController(ServiceCadastrarPessoa serviceCadastrarPessoa){
        this.serviceCadastrarPessoa = serviceCadastrarPessoa;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPessoaDto dados){
        return serviceCadastrarPessoa.execute(dados);
    }
}
