package com.luannascimento.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luannascimento.cursomc.domains.Pedido;
import com.luannascimento.cursomc.exceptions.ObjectNotFoundException;
import com.luannascimento.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Optional<Pedido> buscar(Integer id) {

		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (pedido.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo" + Pedido.class.getName());
		}

		return pedido;

	}

}
