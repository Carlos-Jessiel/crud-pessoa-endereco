package com.api.attornatus.dto.pessoa;

import com.api.attornatus.dto.endereco.DadosRetornoCadastroEnderecoDto;
import com.api.attornatus.model.Endereco;
import com.api.attornatus.model.Pessoa;

import java.time.LocalDate;

public record DadosRetornoCadastroPessoaDto(

        Long id,
        String nome,
        LocalDate dataDeNascimento,
        DadosRetornoCadastroEnderecoDto endereco
) {
    public DadosRetornoCadastroPessoaDto(Pessoa modelPessoa, Endereco modelEndereco) {
        this(modelPessoa.getId(),
                modelPessoa.getNome(),
                modelPessoa.getDataDeNascimento(),
                new DadosRetornoCadastroEnderecoDto(modelEndereco));
    }
}
