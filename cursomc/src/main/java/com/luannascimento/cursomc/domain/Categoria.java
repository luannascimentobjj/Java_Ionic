package com.luannascimento.cursomc.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode
@AllArgsConstructor


public class Categoria implements Serializable{
		private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
		

}
