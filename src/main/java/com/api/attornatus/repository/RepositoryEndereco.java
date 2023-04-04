package com.api.attornatus.repository;

import com.api.attornatus.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEndereco extends JpaRepository<Endereco, Long> {

}
