package com.luannascimento.cursomc.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luannascimento.cursomc.domains.Cliente;
import com.luannascimento.cursomc.dto.ClienteDTO;
import com.luannascimento.cursomc.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Cliente>> listar(@PathVariable("id") Integer id) {

		Optional<Cliente> cliente1 = service.buscar(id);

		return ResponseEntity.ok().body(cliente1);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar( @PathVariable("id") Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {

		service.atualizar(service.convertFromDTO(id, clienteDTO));
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {

		service.deletar(id);
		return ResponseEntity.noContent().build();

	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listarTodos() {

		List<ClienteDTO> cat1 = service.buscarTodos();

		return ResponseEntity.ok().body(cat1);
	}

	@GetMapping(value= "/paginar")
	public ResponseEntity<Page<ClienteDTO>> listarPorPagina(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhaPorPagina", defaultValue = "24") Integer linhaPorPagina,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderarPor", defaultValue = "nome") String orderarPor) {

		 Page<ClienteDTO> catPaginada =  service.buscaPaginada(pagina, linhaPorPagina, orderarPor, direction);

		return ResponseEntity.ok().body(catPaginada);
	}

	
}
