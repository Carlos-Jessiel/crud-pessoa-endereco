package com.api.attornatus.dto.pessoa;

import com.api.attornatus.dto.endereco.DadosRetornoConsultarEnderecoDto;
import com.api.attornatus.model.Endereco;
import com.api.attornatus.model.Pessoa;

import java.time.LocalDate;

public record DadosRetornoConsultarPessoaDto(

        Long id,
        String nome,
        LocalDate dataDeNascimento,
        DadosRetornoConsultarEnderecoDto endereco
) {
    public DadosRetornoConsultarPessoaDto(Pessoa modelPessoa, Endereco modelEndereco){
        this(modelPessoa.getId(),
                modelPessoa.getNome(),
                modelPessoa.getDataDeNascimento(),
                new DadosRetornoConsultarEnderecoDto(modelEndereco));
    }
}
