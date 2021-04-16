package com.brasilservicos.demo.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.time.Duration;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@AllArgsConstructor
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@FieldDefaults(level = PRIVATE)
public class ServicoDoPedido {
	@Id
	@GeneratedValue
	@Type(type = "uuid-char")
	UUID id;

	@OneToOne
	@NonNull
	Servico servico;

	@NonNull
	Duration duracao;

	@NonNull
	@ManyToOne
	PedidoDeServico pedido;
}
