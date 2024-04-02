package com.luannascimento.cursomc.domains.enums;

import lombok.Getter;

@Getter
public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");

	private int cod;
	private String descricao;

	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public static Perfil toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (Perfil perfis : Perfil.values()) {
			if (cod.equals(perfis.getCod())) {
				return perfis;
			}

		}

		throw new IllegalArgumentException("Id Inválido: " + cod);
	}

}
