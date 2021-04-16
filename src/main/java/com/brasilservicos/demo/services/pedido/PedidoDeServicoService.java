package com.brasilservicos.demo.services.pedido;

import com.brasilservicos.demo.models.*;
import com.brasilservicos.demo.repositories.PedidoDeServicoRepository;
import com.brasilservicos.demo.repositories.ServicoDoPedidoRepository;
import com.brasilservicos.demo.repositories.ServicoRepository;
import com.brasilservicos.demo.services.exceptions.PedidoNotFoundException;
import com.brasilservicos.demo.services.pedido.commands.AddPedidoDeServicoCommand;
import com.brasilservicos.demo.services.pedido.commands.TerminaPedidoCommand;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class PedidoDeServicoService {
	PedidoDeServicoRepository pedidoDeServicoRepository;
	ServicoRepository servicoRepository;
	ServicoDoPedidoRepository servicoDoPedidoRepository;

	@Transactional
	public PedidoDeServico create() {
		var pedido = new PedidoDeServico();
		pedido.setStatus(StatusDoPedidoDeServico.ABERTO);
		return this.pedidoDeServicoRepository.save(pedido);
	}

	@Transactional
	public void addServico(AddPedidoDeServicoCommand command) {
		var pedido = this.pedidoDeServicoRepository.getOne(command.getPedido());
		var servico = this.servicoRepository.getOne(command.getServico());
		var duracao = Duration
				.ofHours(command.getHoras())
				.plusMinutes(command.getMinutos());
		var servicoDoPedido = new ServicoDoPedido(servico, duracao, pedido);
		this.servicoDoPedidoRepository.save(servicoDoPedido);
	}

	@Transactional
	public void removeServico(UUID servicoDoPedidoId) {
		this.servicoDoPedidoRepository.deleteById(servicoDoPedidoId);
	}

	@Transactional
	public void terminaPedido(TerminaPedidoCommand command) {
		var pedido = this.pedidoDeServicoRepository.findById(command.getId())
				.orElseThrow(PedidoNotFoundException::new);
		pedido.setNomeDoProfissional(command.getNomeDoProfissional());
		pedido.setImposto(command.getImposto());
		pedido.setStatus(StatusDoPedidoDeServico.EM_ANDAMENTO);

		this.pedidoDeServicoRepository.save(pedido);
	}

	@Transactional
	public void finalizaPedido(UUID pedidoId) {
		this.pedidoDeServicoRepository.updateStatus(pedidoId, StatusDoPedidoDeServico.FINALIZADO);
	}

	public Page<PedidoDeServicoOutput> find(Pageable pageable) {
		return this.pedidoDeServicoRepository.findAll(pageable)
				.map(PedidoDeServicoOutput::new);
	}

	public PedidoDeServico get(UUID pedidoId) {
		return this.pedidoDeServicoRepository.findById(pedidoId)
				.orElseThrow(PedidoNotFoundException::new);
	}

	public InfosDoPedidoOutput getInfosDoPedido(UUID idDoPedido) {
		var pedido = this.pedidoDeServicoRepository.findById(idDoPedido)
				.orElseThrow(PedidoNotFoundException::new);
		var totalBruto = pedido.getServicos()
				.stream()
				.map(servicoDoPedido -> {
					var servico = servicoDoPedido.getServico();
					var duracaoEmHoras = servicoDoPedido
							.getDuracao()
							.toHours();
					return servico.getValorHora().multiply(BigDecimal.valueOf(duracaoEmHoras));
				}).reduce(BigDecimal.ZERO, BigDecimal::add);
		var valorDeImposto = totalBruto.multiply(pedido.getImposto());

		var lucroLiquido = totalBruto.subtract(valorDeImposto);
		var percentualDeLucro = lucroLiquido.divide(totalBruto, RoundingMode.HALF_EVEN)
				.multiply(new BigDecimal("100"));

		return new InfosDoPedidoOutput(totalBruto, percentualDeLucro);
	}
}
