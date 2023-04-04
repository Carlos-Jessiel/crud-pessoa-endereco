package com.api.attornatus.controller;

import com.api.attornatus.dto.endereco.DadosCadastroEnderecoDto;
import com.api.attornatus.service.endereco.ServiceCadastrarNovoEndereco;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final ServiceCadastrarNovoEndereco serviceCadastrarNovoEndereco;

    public EnderecoController(ServiceCadastrarNovoEndereco serviceCadastrarNovoEndereco) {
        this.serviceCadastrarNovoEndereco = serviceCadastrarNovoEndereco;
    }

    @PostMapping("{id}")
    public ResponseEntity cadastrarNovoEndereco(@PathVariable Long id, @RequestBody @Valid DadosCadastroEnderecoDto dados) {
        return serviceCadastrarNovoEndereco.execute(id, dados);
    }

}
