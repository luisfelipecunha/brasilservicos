package com.brasilservicos.demo.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@AllArgsConstructor
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class PedidoDeServico {
	@Id
	@GeneratedValue
	@Type(type = "uuid-char")
	UUID id;

	@OneToMany(
			mappedBy = "pedido",
			fetch = FetchType.LAZY
	)
	List<ServicoDoPedido> servicos;

	@NonNull
	String nomeDoProfissional;

	BigDecimal imposto;

	@NonNull
	@Enumerated(EnumType.STRING)
	StatusDoPedidoDeServico status;
}
