package com.api.attornatus.controller;

import com.api.attornatus.dto.endereco.DadosCadastroEnderecoDto;
import com.api.attornatus.dto.endereco.DadosDefinirEnderecoPrincipalDto;
import com.api.attornatus.dto.endereco.DadosListagemEnderecoDto;
import com.api.attornatus.service.endereco.ServiceCadastrarNovoEndereco;
import com.api.attornatus.service.endereco.ServiceDefinirEnderecoPrincipal;
import com.api.attornatus.service.endereco.ServiceListarEnderecos;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
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
    public ResponseEntity cadastrarNovoEndereco(@PathVariable Long id, @RequestBody @Valid DadosCadastroEnderecoDto dados) {
        return serviceCadastrarNovoEndereco.execute(id, dados);
    }

    @GetMapping("{id}")
    public ResponseEntity<Page<DadosListagemEnderecoDto>> listarEnderecos(@PathVariable Long id, @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        return serviceListarEnderecos.execute(id, paginacao);
    }

    @PutMapping
    public ResponseEntity definirEnderecoPrincipal(@RequestBody @Valid DadosDefinirEnderecoPrincipalDto dados){
        return serviceDefinirEnderecoPrincipal.execute(dados);
    }
}
