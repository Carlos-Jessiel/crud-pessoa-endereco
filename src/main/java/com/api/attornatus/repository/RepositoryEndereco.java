package com.api.attornatus.repository;

import com.api.attornatus.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEndereco extends JpaRepository<Endereco, Long> {

    Page<Endereco> findAllByPessoaId(Long id, Pageable paginacao);

}
