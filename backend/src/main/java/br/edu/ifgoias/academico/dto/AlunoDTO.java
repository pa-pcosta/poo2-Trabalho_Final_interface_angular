package br.edu.ifgoias.academico.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {

	private Integer idaluno;

	@NotNull(message = "O nome é obrigatório")
	@NotBlank(message = "O nome é obrigatório")
	@Size(max = 120, message = "O nome deve ter no máximo 120 caracteres")
	private String nome;

	@NotNull(message = "O sexo é obrigatório")
	@NotBlank(message = "O sexo é obrigatório")
	@Size(max = 1, message = "Use 'M' ou 'F'")
	private String sexo;

	@NotNull(message = "A data de nascimento é obrigatória")
	@Past(message = "A data de nascimento deve ser no passado")
	private LocalDate dt_nasc;
}
