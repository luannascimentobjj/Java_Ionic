package com.luannascimento.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luannascimento.cursomc.domains.Cliente;
import com.luannascimento.cursomc.exceptions.ObjectNotFoundException;
import com.luannascimento.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Optional<Cliente> buscar(Integer id) {

		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (cliente.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo" + Cliente.class.getName());
		}

		return cliente;

	}

}
