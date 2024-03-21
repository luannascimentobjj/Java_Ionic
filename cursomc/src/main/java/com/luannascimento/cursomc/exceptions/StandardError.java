package com.luannascimento.cursomc.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String msg;
	private Long timeStamp;

}
