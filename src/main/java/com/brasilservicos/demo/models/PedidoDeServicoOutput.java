package com.brasilservicos.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class PedidoDeServicoOutput {
	UUID id;

	String nomeDoProfissional;

	BigDecimal imposto;

	StatusDoPedidoDeServico status;

	public PedidoDeServicoOutput(PedidoDeServico pedido) {
		this(
			pedido.getId(),
			pedido.getNomeDoProfissional(),
			pedido.getImposto(),
			pedido.getStatus()
		);
	}
}
