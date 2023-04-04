package com.api.attornatus.service.endereco;

import com.api.attornatus.dto.endereco.DadosListagemEnderecoDto;
import com.api.attornatus.repository.RepositoryEndereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceListarEnderecos {

    private final RepositoryEndereco repositoryEndereco;

    public ServiceListarEnderecos(RepositoryEndereco repositoryEndereco){
        this.repositoryEndereco = repositoryEndereco;
    }

    public ResponseEntity<Page<DadosListagemEnderecoDto>> execute(Long id, Pageable paginacao) {
        var modelEndereco = repositoryEndereco.findAllByPessoaId(id, paginacao).map(DadosListagemEnderecoDto::new);

        return ResponseEntity.status(HttpStatus.OK).body(modelEndereco);
    }
}
