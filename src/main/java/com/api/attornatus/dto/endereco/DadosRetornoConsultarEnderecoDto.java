package com.api.attornatus.dto.endereco;

import com.api.attornatus.model.Endereco;

public record DadosRetornoConsultarEnderecoDto(

        Long id,
        Boolean principal,
        String logradouro,
        String cep,
        String numero,
        String cidade
){
    public DadosRetornoConsultarEnderecoDto(Endereco modelEndereco){
        this(modelEndereco.getId(),
                modelEndereco.getPrincipal(),
                modelEndereco.getLogradouro(),
                modelEndereco.getCep(),
                modelEndereco.getNumero(),
                modelEndereco.getCidade());
    }
}
