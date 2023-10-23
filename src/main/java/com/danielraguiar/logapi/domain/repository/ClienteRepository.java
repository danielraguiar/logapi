package com.danielraguiar.logapi.domain.repository;

import com.danielraguiar.logapi.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    List<Cliente> findByNome(String nome);
}
