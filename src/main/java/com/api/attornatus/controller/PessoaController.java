package com.api.attornatus.controller;

import com.api.attornatus.dto.pessoa.DadosAtualizacaoPessoaDto;
import com.api.attornatus.dto.pessoa.DadosCadastroPessoaDto;
import com.api.attornatus.dto.pessoa.DadosListagemPessoasDto;
import com.api.attornatus.service.pessoa.ServiceAtualizarDadosPessoa;
import com.api.attornatus.service.pessoa.ServiceCadastrarPessoa;
import com.api.attornatus.service.pessoa.ServiceConsultarPessoa;
import com.api.attornatus.service.pessoa.ServiceListarPessoas;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final ServiceCadastrarPessoa serviceCadastrarPessoa;

    private final ServiceConsultarPessoa serviceConsultarPessoa;

    private final ServiceAtualizarDadosPessoa serviceAtualizarDadosPessoa;

    private final ServiceListarPessoas serviceListarPessoas;

    public PessoaController(ServiceCadastrarPessoa serviceCadastrarPessoa,
                            ServiceConsultarPessoa serviceConsultarPessoa,
                            ServiceAtualizarDadosPessoa serviceAtualizarDadosPessoa,
                            ServiceListarPessoas serviceListarPessoas){
        this.serviceCadastrarPessoa = serviceCadastrarPessoa;
        this.serviceConsultarPessoa = serviceConsultarPessoa;
        this.serviceAtualizarDadosPessoa = serviceAtualizarDadosPessoa;
        this.serviceListarPessoas = serviceListarPessoas;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPessoaDto dados){
        return serviceCadastrarPessoa.execute(dados);
    }

    @GetMapping("{id}")
    public ResponseEntity consultarPessoa(@PathVariable Long id){
        return serviceConsultarPessoa.execute(id);
    }

    @PutMapping
    public ResponseEntity atualizarDadosPessoa(@RequestBody @Valid DadosAtualizacaoPessoaDto dados){
        return serviceAtualizarDadosPessoa.execute(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPessoasDto>> listarPessoas(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        return serviceListarPessoas.execute(paginacao);
    }
}
