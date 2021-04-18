package com.brasilservicos.demo.controllers;

import com.brasilservicos.demo.models.Servico;
import com.brasilservicos.demo.services.servico.ServicoService;
import com.brasilservicos.demo.services.servico.commands.CreateServicoCommand;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/servicos")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ServicoController {
	static String BASE_URL = "/servicos";
	ServicoService servicoService;

	@PostMapping
	public ResponseEntity<Servico> create(
			@Valid @RequestBody CreateServicoCommand command,
			UriComponentsBuilder uriComponentsBuilder
	) {
		var servico = this.servicoService.create(command);

		var uri = uriComponentsBuilder.path(BASE_URL)
				.buildAndExpand(servico.getId())
				.toUri();

		return ResponseEntity
				.created(uri)
				.body(servico);
	}

	@GetMapping
	public ResponseEntity<Page<Servico>> find(
			@RequestParam(required = false) String busca,
			@PageableDefault Pageable pageable
	) {
		var servicos = this.servicoService.find(busca, pageable);
		return ResponseEntity.ok(servicos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Servico> get(
			@PathVariable UUID id
	) {
		var servico = this.servicoService.get(id);
		return ResponseEntity.ok(servico);
	}
}
