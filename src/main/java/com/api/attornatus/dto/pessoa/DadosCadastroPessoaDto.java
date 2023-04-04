package com.api.attornatus.dto.pessoa;

import com.api.attornatus.dto.endereco.DadosCadastroEnderecoDto;
import com.api.attornatus.model.Pessoa;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroPessoaDto(

        @NotBlank
        String nome,
        @NotNull
        LocalDate dataDeNascimento,
        @NotNull
        @Valid
        DadosCadastroEnderecoDto endereco
) {
    public static Pessoa transformModel(DadosCadastroPessoaDto dados) {
        var modelPessoa = new Pessoa();
        modelPessoa.setNome(dados.nome);
        modelPessoa.setDataDeNascimento(dados.dataDeNascimento);
        return modelPessoa;
    }
}
