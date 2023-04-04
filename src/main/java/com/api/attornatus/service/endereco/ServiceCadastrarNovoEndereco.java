package com.api.attornatus.service.endereco;

import com.api.attornatus.dto.endereco.DadosCadastroEnderecoDto;
import com.api.attornatus.dto.pessoa.DadosRetornoCadastroPessoaDto;
import com.api.attornatus.repository.RepositoryEndereco;
import com.api.attornatus.repository.RepositoryPessoa;
import com.api.attornatus.service.endereco.filtro.FiltroEndereco;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceCadastrarNovoEndereco {

    private final RepositoryPessoa repositoryPessoa;

    private final RepositoryEndereco repositoryEndereco;

    public ServiceCadastrarNovoEndereco(RepositoryPessoa repositoryPessoa,
                                        RepositoryEndereco repositoryEndereco) {
        this.repositoryPessoa = repositoryPessoa;
        this.repositoryEndereco = repositoryEndereco;
    }

    @Transactional
    public ResponseEntity execute(Long id, DadosCadastroEnderecoDto dados) {
        if (repositoryPessoa.existsById(id)) {
            var modelPessoa = repositoryPessoa.getReferenceById(id);
            var novoEndereco = DadosCadastroEnderecoDto.transformarModel(dados, modelPessoa);

            var modelEndereco = FiltroEndereco.filtrarEndereco(modelPessoa.getEndereco());

            repositoryPessoa.save(modelPessoa);
            repositoryEndereco.save(modelEndereco);
            repositoryEndereco.save(novoEndereco);

            return ResponseEntity.status(HttpStatus.CREATED).body(new DadosRetornoCadastroPessoaDto(modelPessoa, novoEndereco));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há dados para o ID informado.");
    }

}
