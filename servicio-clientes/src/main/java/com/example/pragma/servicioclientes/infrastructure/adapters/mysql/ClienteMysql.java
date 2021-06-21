package com.example.pragma.servicioclientes.infrastructure.adapters.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteMysql extends JpaRepository<ClienteEntidad, ClientePk> {

    List<ClienteEntidad> findByEdadGreaterThanEqual(Integer edad);
}
