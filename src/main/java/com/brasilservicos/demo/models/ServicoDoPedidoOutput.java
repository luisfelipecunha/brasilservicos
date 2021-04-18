package com.brasilservicos.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ServicoDoPedidoOutput {
	UUID id;
	Servico servico;
	Integer horas;
	Integer minutos;

	public ServicoDoPedidoOutput(ServicoDoPedido servicoDoPedido) {
		this(
			servicoDoPedido.getId(),
			servicoDoPedido.getServico(),
			servicoDoPedido.getDuracao().toHoursPart(),
			servicoDoPedido.getDuracao().toMinutesPart()
		);
	}
}
