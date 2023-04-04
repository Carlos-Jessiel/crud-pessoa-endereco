package com.api.attornatus.dto.pessoa;


import com.api.attornatus.dto.endereco.DadosRetornoConsultarEnderecoDto;
import com.api.attornatus.model.Endereco;
import com.api.attornatus.model.Pessoa;

import java.time.LocalDate;

public record DadosRetornoAtualizacaoPessoaDto(

        Long id,
        String nome,
        LocalDate dataDeNascimento,
        DadosRetornoConsultarEnderecoDto endereco

) {

    public DadosRetornoAtualizacaoPessoaDto(Pessoa modelPessoa, Endereco endereco){
        this(modelPessoa.getId(),
                modelPessoa.getNome(),
                modelPessoa.getDataDeNascimento(),
                new DadosRetornoConsultarEnderecoDto(endereco));
    }
}
