package com.example.pragma.servicioclientes.infrastructure.adapters.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteMysql extends JpaRepository<ClienteEntidad, ClientePk> {
}
