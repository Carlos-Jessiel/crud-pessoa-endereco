package com.api.attornatus.dto.endereco;

import com.api.attornatus.model.Endereco;

public record DadosRetornoCadastroEnderecoDto(

        String logradouro,
        String cep,
        String numero,
        String cidade
){
    public DadosRetornoCadastroEnderecoDto(Endereco modelEndereco){
        this(modelEndereco.getLogradouro(),
                modelEndereco.getCep(),
                modelEndereco.getNumero(),
                modelEndereco.getCidade());
    }

}
