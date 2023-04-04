package com.api.attornatus.controller;

import com.api.attornatus.dto.pessoa.*;
import com.api.attornatus.service.pessoa.ServiceAtualizarDadosPessoa;
import com.api.attornatus.service.pessoa.ServiceCadastrarPessoa;
import com.api.attornatus.service.pessoa.ServiceConsultarPessoa;
import com.api.attornatus.service.pessoa.ServiceListarPessoas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
@Tag(name = "Pessoa", description = "Cadastro, listagem, consulta e atualização de dados.")
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
    @Operation(summary = "Cadastrar pessoa", description = "Cadastra nova pessoa e endereço.")
    @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosRetornoCadastroPessoaDto.class))})
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPessoaDto dados){
        return serviceCadastrarPessoa.execute(dados);
    }

    @GetMapping("{id}")
    @Operation(summary = "Consultar uma pessoa", description = "Consulta uma pessoa específica pelo ID informado.")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosRetornoConsultarPessoaDto.class))})
    public ResponseEntity consultarPessoa(@PathVariable Long id){
        return serviceConsultarPessoa.execute(id);
    }

    @PutMapping
    @Operation(summary = "Atualizar dados pessoa", description = "Atualiza todos ou partes dos dados da pessoa e/ou endereço.")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosRetornoCadastroPessoaDto.class))})
    public ResponseEntity atualizarDadosPessoa(@RequestBody @Valid DadosAtualizacaoPessoaDto dados){
        return serviceAtualizarDadosPessoa.execute(dados);
    }

    @GetMapping
    @Operation(summary = "Listar pessoas", description = "Lista todas as pessoas, trazendo os dados paginados por padrao contendo 10 dados ordenados por ID.")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosRetornoCadastroPessoaDto.class))})
    public ResponseEntity<Page<DadosListagemPessoasDto>> listarPessoas(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        return serviceListarPessoas.execute(paginacao);
    }
}
