package com.api.attornatus.dto.endereco;


import com.api.attornatus.model.Endereco;

public record DadosListagemEnderecoDto(

        Long id,
        Boolean principal,
        String logradouro,
        String cep,
        String numero,
        String cidade
) {
    public DadosListagemEnderecoDto(Endereco modelEndereco){
        this(modelEndereco.getId(),
                modelEndereco.getPrincipal(),
                modelEndereco.getLogradouro(),
                modelEndereco.getCep(),
                modelEndereco.getNumero(),
                modelEndereco.getCidade());
    }
}
