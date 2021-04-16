package com.brasilservicos.demo.repositories;

import com.brasilservicos.demo.models.ServicoDoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServicoDoPedidoRepository extends JpaRepository<ServicoDoPedido, UUID> {}
