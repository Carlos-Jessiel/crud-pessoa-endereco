package com.api.attornatus.dto.pessoa;

import com.api.attornatus.dto.endereco.DadosAtualizacaoEnderecoDto;
import com.api.attornatus.model.Pessoa;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizacaoPessoaDto(

        @NotNull
        Long id,
        String nome,
        LocalDate dataDeNascimento,
        DadosAtualizacaoEnderecoDto endereco

) {
    public static Pessoa atualizarModel(Pessoa modelPessoa, DadosAtualizacaoPessoaDto dados) {
        if (dados.nome != null && !dados.nome.isEmpty()){
            modelPessoa.setNome(dados.nome);
        }
        if (dados.dataDeNascimento != null){
            modelPessoa.setDataDeNascimento(dados.dataDeNascimento);
        }
        return modelPessoa;
    }
}
