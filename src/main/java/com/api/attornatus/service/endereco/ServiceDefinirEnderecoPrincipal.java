package com.api.attornatus.service.endereco;

import com.api.attornatus.dto.endereco.DadosDefinirEnderecoPrincipalDto;
import com.api.attornatus.dto.pessoa.DadosRetornoConsultarPessoaDto;
import com.api.attornatus.repository.RepositoryEndereco;
import com.api.attornatus.repository.RepositoryPessoa;
import com.api.attornatus.service.endereco.filtro.FiltroEndereco;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceDefinirEnderecoPrincipal {

    private final RepositoryPessoa repositoryPessoa;

    private final RepositoryEndereco repositoryEndereco;

    public ServiceDefinirEnderecoPrincipal(RepositoryPessoa repositoryPessoa,
                                           RepositoryEndereco repositoryEndereco) {
        this.repositoryPessoa = repositoryPessoa;
        this.repositoryEndereco = repositoryEndereco;
    }

    @Transactional
    public ResponseEntity execute(DadosDefinirEnderecoPrincipalDto dados) {
        if (repositoryPessoa.existsById(dados.idPessoa())){
            var modelPessoa = repositoryPessoa.getReferenceById(dados.idPessoa());
            var modelEndereco = FiltroEndereco.filtrarEnderecoEAlterarStatus(dados, modelPessoa);

            if (modelEndereco == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há endereço para o ID informado.");
            }

            repositoryEndereco.save(modelEndereco);

            return ResponseEntity.status(HttpStatus.OK).body(new DadosRetornoConsultarPessoaDto(modelPessoa, modelEndereco));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há dados para o ID informado.");
    }
}
