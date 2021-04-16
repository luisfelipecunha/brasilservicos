package com.brasilservicos.demo.repositories;

import com.brasilservicos.demo.models.PedidoDeServico;
import com.brasilservicos.demo.models.Servico;
import com.brasilservicos.demo.models.StatusDoPedidoDeServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoDeServicoRepository extends JpaRepository<PedidoDeServico, UUID> {
	@Modifying
	@Query("update PedidoDeServico set status = ?2 where id = ?1")
	void updateStatus(UUID id, StatusDoPedidoDeServico finalizado);
}
