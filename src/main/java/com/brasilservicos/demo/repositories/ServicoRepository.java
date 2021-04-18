package com.brasilservicos.demo.repositories;

import com.brasilservicos.demo.models.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, UUID> {
	@Query(
		"SELECT s FROM Servico s WHERE (UPPER(s.nome) like :nome or :nome is null)"
	)
	Page<Servico> findByNomeLike(String nome, Pageable pageable);
}
