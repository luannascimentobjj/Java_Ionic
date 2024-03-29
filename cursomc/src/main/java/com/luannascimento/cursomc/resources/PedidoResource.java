package com.luannascimento.cursomc.resources;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luannascimento.cursomc.domains.Pedido;
import com.luannascimento.cursomc.dto.PedidoDTO;
import com.luannascimento.cursomc.services.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Pedido>> listar(@PathVariable("id") Integer id) {

		Optional<Pedido> ped1 = service.buscar(id);

		return ResponseEntity.ok().body(ped1);
	}
	

	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody PedidoDTO pedidoDTO) {
		
		
		Pedido pedido = service.inserir(service.convertFromDTO(pedidoDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

}
