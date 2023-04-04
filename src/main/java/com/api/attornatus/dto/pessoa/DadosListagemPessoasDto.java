package com.api.attornatus.dto.pessoa;

import com.api.attornatus.model.Pessoa;

import java.time.LocalDate;

public record DadosListagemPessoasDto(

        Long id,
        String nome,
        LocalDate dataDeNascimento
) {
    public DadosListagemPessoasDto(Pessoa modelPessoa){
        this(modelPessoa.getId(),
                modelPessoa.getNome(),
                modelPessoa.getDataDeNascimento());
    }
}
