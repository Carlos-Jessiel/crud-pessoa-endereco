package com.api.attornatus.service.pessoa;

import com.api.attornatus.dto.pessoa.DadosListagemPessoasDto;
import com.api.attornatus.repository.RepositoryPessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceListarPessoas {

    private final RepositoryPessoa repositoryPessoa;

    public ServiceListarPessoas(RepositoryPessoa repositoryPessoa){
        this.repositoryPessoa = repositoryPessoa;
    }

    public ResponseEntity<Page<DadosListagemPessoasDto>> execute(Pageable paginacao) {
        var listaPessoas = repositoryPessoa.findAll(paginacao).map(DadosListagemPessoasDto::new);

        return ResponseEntity.status(HttpStatus.OK).body(listaPessoas);
    }
}
