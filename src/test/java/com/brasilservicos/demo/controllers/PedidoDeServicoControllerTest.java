package com.brasilservicos.demo.controllers;

import com.brasilservicos.demo.BrasilServicosApplicationTests;
import com.brasilservicos.demo.models.Servico;
import com.brasilservicos.demo.repositories.PedidoDeServicoRepository;
import com.brasilservicos.demo.repositories.ServicoDoPedidoRepository;
import com.brasilservicos.demo.repositories.ServicoRepository;
import com.brasilservicos.demo.services.servico.ServicoService;
import com.brasilservicos.demo.services.servico.commands.CreateServicoCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FieldDefaults(level = PRIVATE)
public class PedidoDeServicoControllerTest extends BrasilServicosApplicationTests {
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ServicoRepository servicoRepository;

	@Autowired
	PedidoDeServicoRepository pedidoDeServicoRepository;

	@Autowired
	ServicoDoPedidoRepository servicoDoPedidoRepository;

	@Autowired
	ServicoService servicoService;

	Servico servico;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
		this.servico = this.servicoService.create(
				new CreateServicoCommand("Lavagem", BigDecimal.ONE)
		);
	}

	@AfterEach
	public void tearDown() {
		servicoDoPedidoRepository.deleteAll();
		pedidoDeServicoRepository.deleteAll();
		servicoDoPedidoRepository.deleteAll();
	}

	@Test
	public void criaPedido_201() throws Exception {
		this.mockMvc.perform(
				post(PedidoDeServicoController.BASE_URL)
		)
		.andDo(print())
		.andExpect(status().isCreated());
	}
}
