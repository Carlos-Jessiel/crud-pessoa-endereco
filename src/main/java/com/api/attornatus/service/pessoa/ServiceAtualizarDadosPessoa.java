package com.api.attornatus.service.pessoa;

import com.api.attornatus.dto.endereco.DadosAtualizacaoEnderecoDto;
import com.api.attornatus.dto.pessoa.DadosAtualizacaoPessoaDto;
import com.api.attornatus.dto.pessoa.DadosRetornoAtualizacaoPessoaDto;
import com.api.attornatus.repository.RepositoryEndereco;
import com.api.attornatus.repository.RepositoryPessoa;
import com.api.attornatus.service.endereco.filtro.FiltroEndereco;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceAtualizarDadosPessoa {

    private final RepositoryPessoa repositoryPessoa;

    private final RepositoryEndereco repositoryEndereco;

    public ServiceAtualizarDadosPessoa(RepositoryPessoa repositoryPessoa,
                                       RepositoryEndereco repositoryEndereco){
        this.repositoryPessoa = repositoryPessoa;
        this.repositoryEndereco = repositoryEndereco;
    }

    @Transactional
    public ResponseEntity execute(DadosAtualizacaoPessoaDto dados) {
        if (repositoryPessoa.existsById(dados.id())){
            var modelPessoa = repositoryPessoa.getReferenceById(dados.id());
            var modelAtualizadaPessoa = DadosAtualizacaoPessoaDto.atualizarModel(modelPessoa, dados);

            var modelAtualizadaEndereco = DadosAtualizacaoEnderecoDto.atualizarModel(dados.endereco(), FiltroEndereco.filtrarEnderecoPrimario(modelPessoa.getEndereco()));

            repositoryPessoa.save(modelAtualizadaPessoa);
            repositoryEndereco.save(modelAtualizadaEndereco);

            return ResponseEntity.status(HttpStatus.OK).body(new DadosRetornoAtualizacaoPessoaDto(modelAtualizadaPessoa, modelAtualizadaEndereco));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há dados para o ID informado.");
    }
}
