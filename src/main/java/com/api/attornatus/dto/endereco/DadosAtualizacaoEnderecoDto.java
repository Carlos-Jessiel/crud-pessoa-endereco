package com.api.attornatus.dto.endereco;


import com.api.attornatus.model.Endereco;

public record DadosAtualizacaoEnderecoDto(

        String logradouro,
        String cep,
        String numero,
        String cidade
) {
    public static Endereco atualizarModel(DadosAtualizacaoEnderecoDto dadosAtualizacaoEndereco, Endereco modelEndereco) {
        if (dadosAtualizacaoEndereco.logradouro != null && !dadosAtualizacaoEndereco.logradouro.isEmpty()){
            modelEndereco.setLogradouro(dadosAtualizacaoEndereco.logradouro);
        }
        if (dadosAtualizacaoEndereco.cep != null && !dadosAtualizacaoEndereco.cep.isEmpty()){
            modelEndereco.setCep(dadosAtualizacaoEndereco.cep);
        }
        if (dadosAtualizacaoEndereco.numero != null && !dadosAtualizacaoEndereco.numero.isEmpty()){
            modelEndereco.setNumero(dadosAtualizacaoEndereco.numero);
        }
        if (dadosAtualizacaoEndereco.cidade != null && !dadosAtualizacaoEndereco.cidade.isEmpty()){
            modelEndereco.setCidade(dadosAtualizacaoEndereco.cidade);
        }
        return modelEndereco;
    }
}
