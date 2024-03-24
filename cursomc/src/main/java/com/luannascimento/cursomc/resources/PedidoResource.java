package com.luannascimento.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luannascimento.cursomc.domains.Pedido;
import com.luannascimento.cursomc.services.PedidoService;

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

}
