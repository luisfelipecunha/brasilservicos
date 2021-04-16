package com.brasilservicos.demo.services.exceptions;

public class PedidoNotFoundException extends RuntimeException {
	public PedidoNotFoundException() {
		super("Pedido não encontrado");
	}
}
