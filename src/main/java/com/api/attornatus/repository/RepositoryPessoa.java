package com.api.attornatus.repository;

import com.api.attornatus.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPessoa extends JpaRepository<Pessoa, Long> {
}
