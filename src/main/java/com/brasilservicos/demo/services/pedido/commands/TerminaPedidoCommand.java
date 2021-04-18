package com.brasilservicos.demo.services.pedido.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TerminaPedidoCommand {
	@Setter
	UUID id;
	@NotNull
	String nomeDoProfissional;
	@NotNull
	BigDecimal imposto;
}
