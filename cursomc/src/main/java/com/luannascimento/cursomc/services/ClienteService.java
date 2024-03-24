package com.luannascimento.cursomc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.luannascimento.cursomc.domains.Cliente;
import com.luannascimento.cursomc.dto.ClienteDTO;
import com.luannascimento.cursomc.exceptions.DataIntegrityException;
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
	
	public Cliente inserir(Cliente Cliente) {
		Cliente.setId(null);
		return clienteRepository.save(Cliente);

	}

	public void atualizar(Integer id, Cliente Cliente) {
		buscar(id);
		clienteRepository.save(Cliente);

	}

	public void deletar(Integer id) {

		buscar(id);
		try {

			clienteRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Cliente que possui produtos");
		}

	}

	public List<ClienteDTO> buscarTodos() {

		List<Cliente> listaCliente = clienteRepository.findAll();
		List<ClienteDTO> listDTO = listaCliente.stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());

		if (listDTO.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado!");
		}

		return listDTO;

	}

	public Page<ClienteDTO> buscaPaginada(Integer pagina, Integer linhaPorPagina, String orderarPor, String direction) {

		PageRequest pageRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(direction), orderarPor);
		Page<ClienteDTO> listDTO = clienteRepository.findAll(pageRequest).map(obj -> new ClienteDTO(obj));

		return listDTO;

	}
	
	public Cliente convertFromDTO (ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail());
	}

}
