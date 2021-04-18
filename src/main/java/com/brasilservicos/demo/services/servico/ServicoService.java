package com.brasilservicos.demo.services.servico;

import com.brasilservicos.demo.models.Servico;
import com.brasilservicos.demo.repositories.ServicoRepository;
import com.brasilservicos.demo.services.exceptions.ServicoNotFoundException;
import com.brasilservicos.demo.services.servico.commands.CreateServicoCommand;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ServicoService {
	ServicoRepository servicoRepository;

	public Servico create(CreateServicoCommand createServicoCommand) {
		var servico = new Servico(
				createServicoCommand.getNome(),
				createServicoCommand.getValorHora()
		);

		return this.servicoRepository.save(servico);
	}

	public Page<Servico> find(
			String busca,
			Pageable pageable
	) {
		var buscaFormatada = busca != null && !busca.isBlank()
				? String.format("%%s%%", busca.toUpperCase())
				: null;

		return this.servicoRepository
				.findByNomeLike(buscaFormatada, pageable);
	}

	public Servico get(UUID idDoServico) {
		return this.servicoRepository.findById(idDoServico)
				.orElseThrow(ServicoNotFoundException::new);
	}
}
