package com.api.attornatus.dto.endereco;

import com.api.attornatus.model.Endereco;
import com.api.attornatus.model.Pessoa;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEnderecoDto(

        @NotBlank
        String logradouro,
        @NotBlank
        String cep,
        @NotBlank
        String numero,
        @NotBlank
        String cidade
) {
    public static Endereco transformarModel(DadosCadastroEnderecoDto dados, Pessoa modelPessoa) {
        var modelEndereco = new Endereco();
        modelEndereco.setLogradouro(dados.logradouro);
        modelEndereco.setPessoa(modelPessoa);
        modelEndereco.setPrincipal(true);
        modelEndereco.setCep(dados.cep);
        modelEndereco.setNumero(dados.numero);
        modelEndereco.setCidade(dados.cidade);

        return modelEndereco;
    }
}
