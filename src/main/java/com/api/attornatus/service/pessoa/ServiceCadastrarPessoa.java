package com.api.attornatus.service.pessoa;

import com.api.attornatus.dto.endereco.DadosCadastroEnderecoDto;
import com.api.attornatus.dto.pessoa.DadosCadastroPessoaDto;
import com.api.attornatus.dto.pessoa.DadosRetornoCadastroPessoaDto;
import com.api.attornatus.repository.RepositoryEndereco;
import com.api.attornatus.repository.RepositoryPessoa;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceCadastrarPessoa {

    private final RepositoryPessoa repositoryPessoa;
    private final RepositoryEndereco repositoryEndereco;

    public ServiceCadastrarPessoa(RepositoryPessoa repositoryPessoa,
                                  RepositoryEndereco repositoryEndereco) {
        this.repositoryPessoa = repositoryPessoa;
        this.repositoryEndereco = repositoryEndereco;
    }

    @Transactional
    public ResponseEntity execute(DadosCadastroPessoaDto dados) {
        var modelPessoa = DadosCadastroPessoaDto.transformModel(dados);
        var modelEndereco = DadosCadastroEnderecoDto.transformarModel(dados.endereco(), modelPessoa);

        repositoryPessoa.save(modelPessoa);
        repositoryEndereco.save(modelEndereco);

        return ResponseEntity.status(HttpStatus.CREATED).body(new DadosRetornoCadastroPessoaDto(modelPessoa, modelEndereco));
    }
}
