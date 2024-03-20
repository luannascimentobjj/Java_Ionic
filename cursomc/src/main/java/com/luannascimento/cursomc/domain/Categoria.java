package com.luannascimento.cursomc.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Categoria implements Serializable{
		private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
		

}
