package com.api.attornatus.service.pessoa;

import com.api.attornatus.dto.pessoa.DadosRetornoConsultarPessoaDto;
import com.api.attornatus.repository.RepositoryPessoa;
import com.api.attornatus.service.endereco.filtro.FiltroEndereco;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceConsultarPessoa {

    private final RepositoryPessoa repositoryPessoa;

    public ServiceConsultarPessoa(RepositoryPessoa repositoryPessoa){
        this.repositoryPessoa = repositoryPessoa;
    }

    public ResponseEntity execute(Long id) {
        if (repositoryPessoa.existsById(id)){
            var model = repositoryPessoa.getReferenceById(id);
            var modelEndereco = FiltroEndereco.filtrarEnderecoPrimario(model.getEndereco());

            return ResponseEntity.status(HttpStatus.OK).body(new DadosRetornoConsultarPessoaDto(model, modelEndereco));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há dados para o ID informado.");
    }
}
