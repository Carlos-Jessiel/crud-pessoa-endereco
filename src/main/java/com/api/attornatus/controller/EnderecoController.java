package com.api.attornatus.controller;

import com.api.attornatus.dto.endereco.DadosCadastroEnderecoDto;
import com.api.attornatus.dto.endereco.DadosDefinirEnderecoPrincipalDto;
import com.api.attornatus.dto.endereco.DadosListagemEnderecoDto;
import com.api.attornatus.dto.pessoa.DadosRetornoCadastroPessoaDto;
import com.api.attornatus.service.endereco.ServiceCadastrarNovoEndereco;
import com.api.attornatus.service.endereco.ServiceDefinirEnderecoPrincipal;
import com.api.attornatus.service.endereco.ServiceListarEnderecos;
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
@RequestMapping("/endereco")
@Tag(name = "Endereço", description = "Cadastro, listagem e alteração de endereço.")
public class EnderecoController {

    private final ServiceCadastrarNovoEndereco serviceCadastrarNovoEndereco;

    private final ServiceListarEnderecos serviceListarEnderecos;

    private final ServiceDefinirEnderecoPrincipal serviceDefinirEnderecoPrincipal;

    public EnderecoController(ServiceCadastrarNovoEndereco serviceCadastrarNovoEndereco,
                              ServiceListarEnderecos serviceListarEnderecos,
                              ServiceDefinirEnderecoPrincipal serviceDefinirEnderecoPrincipal) {
        this.serviceCadastrarNovoEndereco = serviceCadastrarNovoEndereco;
        this.serviceListarEnderecos = serviceListarEnderecos;
        this.serviceDefinirEnderecoPrincipal = serviceDefinirEnderecoPrincipal;
    }

    @PostMapping("{id}")
    @Operation(summary = "Cadastrar novo endereço", description = "Cadastra novo endereço passando ID da pessoa e as informações referentes ao novo endereço")
    @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosRetornoCadastroPessoaDto.class))})
    public ResponseEntity cadastrarNovoEndereco(@PathVariable Long id, @RequestBody @Valid DadosCadastroEnderecoDto dados) {
        return serviceCadastrarNovoEndereco.execute(id, dados);
    }

    @GetMapping("{id}")
    @Operation(summary = "Listar endereços", description = "Lista todos os endereços de uma pessoa paginados por padrao contendo 10 dados ordenados por ID.")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosListagemEnderecoDto.class))})
    public ResponseEntity<Page<DadosListagemEnderecoDto>> listarEnderecos(@PathVariable Long id, @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        return serviceListarEnderecos.execute(id, paginacao);
    }

    @PutMapping
    @Operation(summary = "Alterar endereço principal", description = "Passando o ID da pessoa e o ID do endereço desejado para ser o principal, será alterado o endereço principal desta pessoa.")
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DadosListagemEnderecoDto.class))})
    public ResponseEntity definirEnderecoPrincipal(@RequestBody @Valid DadosDefinirEnderecoPrincipalDto dados){
        return serviceDefinirEnderecoPrincipal.execute(dados);
    }
}
