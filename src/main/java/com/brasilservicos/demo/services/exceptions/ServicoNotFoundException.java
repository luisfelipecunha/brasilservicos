package com.brasilservicos.demo.services.exceptions;

public class ServicoNotFoundException extends RuntimeException {
	public ServicoNotFoundException() {
		super("Serviço não encontrado");
	}
}
