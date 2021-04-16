package com.brasilservicos.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class InfosDoPedidoOutput {
	BigDecimal valorTotalDoPedido;
	BigDecimal percentualDeLucro;
}
