package com.brasilservicos.demo.controllers;

import com.brasilservicos.demo.services.exceptions.PedidoNotFoundException;
import com.brasilservicos.demo.services.exceptions.ServicoNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
	final MessageSource messageSource;

	public ErrorHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler({
			PedidoNotFoundException.class,
			ServicoNotFoundException.class
	})
	public ResponseEntity<List<String>> handleNotFoundException(RuntimeException ex) {
		List<String> mensagem = new ArrayList<>();
		mensagem.add(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
	}
}
