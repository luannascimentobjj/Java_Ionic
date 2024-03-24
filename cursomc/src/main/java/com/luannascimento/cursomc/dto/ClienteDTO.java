package com.luannascimento.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.luannascimento.cursomc.domains.Cliente;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message = "Preenchimento Obrigatório!")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	private String email;
	
	
	public ClienteDTO(Cliente cliente) {
		super();
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}
	
	

}
