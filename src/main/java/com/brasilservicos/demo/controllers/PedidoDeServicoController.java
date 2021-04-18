package com.brasilservicos.demo.controllers;


import com.brasilservicos.demo.models.*;
import com.brasilservicos.demo.services.pedido.PedidoDeServicoService;
import com.brasilservicos.demo.services.pedido.commands.AddPedidoDeServicoCommand;
import com.brasilservicos.demo.services.pedido.commands.TerminaPedidoCommand;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class PedidoDeServicoController {
	static String BASE_URL = "/pedidos";
	PedidoDeServicoService pedidoDeServicoService;

	@PostMapping
	public ResponseEntity<PedidoDeServicoOutput> create(UriComponentsBuilder uriComponentsBuilder) {
		var pedidoCriado = this.pedidoDeServicoService.create();
		var uri = uriComponentsBuilder.path(BASE_URL)
				.buildAndExpand(pedidoCriado.getId())
				.toUri();

		return ResponseEntity
				.created(uri)
				.body(pedidoCriado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletePedido(@PathVariable UUID id) {
		this.pedidoDeServicoService.deletePedido(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PostMapping("/{id}/servicos")
	public ResponseEntity<ServicoDoPedidoOutput> addServico(
			@PathVariable UUID id,
			@Valid @RequestBody AddPedidoDeServicoCommand command,
			UriComponentsBuilder uriComponentsBuilder
	) {
		command.setPedido(id);
		var servico = this.pedidoDeServicoService
				.addServico(command);
		var uri = uriComponentsBuilder
				.path(String.format("%s/%s/servicos", BASE_URL, id.toString()))
				.buildAndExpand(servico.getId())
				.toUri();

		return ResponseEntity
				.created(uri)
				.body(new ServicoDoPedidoOutput(servico));
	}

	@DeleteMapping("/servicos/{id}")
	public ResponseEntity<HttpStatus> removeServico(@PathVariable UUID id) {
		this.pedidoDeServicoService.removeServico(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<HttpStatus> terminaPedido(
			@PathVariable UUID id,
			@Valid @RequestBody TerminaPedidoCommand command
	) {
		command.setId(id);
		this.pedidoDeServicoService.terminaPedido(command);

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<PedidoDeServicoOutput> finalizaPedido(
			@PathVariable UUID id
	) {
		var pedido = this.pedidoDeServicoService.finalizaPedido(id);
		return ResponseEntity.ok(pedido);
	}

	@GetMapping
	public ResponseEntity<Page<PedidoDeServicoOutput>> find(
			@PageableDefault Pageable pageable
	) {
		var pedidos = this.pedidoDeServicoService.find(pageable);
		return ResponseEntity.ok(pedidos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDeServico> get(
			@PathVariable UUID id
	) {
		var pedido = this.pedidoDeServicoService.get(id);
		return ResponseEntity.ok(pedido);
	}

	@GetMapping("/{id}/infos")
	public ResponseEntity<InfosDoPedidoOutput> getInfos(
			@PathVariable UUID id
	) {
		var infos = this.pedidoDeServicoService.getInfosDoPedido(id);
		return ResponseEntity.ok(infos);
	}
}
