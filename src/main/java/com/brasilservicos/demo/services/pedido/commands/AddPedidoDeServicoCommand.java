package com.brasilservicos.demo.services.pedido.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class AddPedidoDeServicoCommand {
	@NotNull
	UUID pedido;
	@NotNull
	UUID servico;
	@NotNull
	Integer horas;
	@NotNull
	Integer minutos;
}
