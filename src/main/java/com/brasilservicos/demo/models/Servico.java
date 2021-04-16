package com.brasilservicos.demo.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@AllArgsConstructor
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@FieldDefaults(level = PRIVATE)
public class Servico {
	@Id
	@GeneratedValue
	@Type(type = "uuid-char")
	UUID id;

	@NonNull
	String nome;

	@NonNull
	BigDecimal valorHora;
}
