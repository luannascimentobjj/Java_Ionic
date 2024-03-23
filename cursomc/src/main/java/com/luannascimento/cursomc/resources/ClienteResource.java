package com.luannascimento.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luannascimento.cursomc.domains.Cliente;
import com.luannascimento.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listar(@PathVariable("id") Integer id) {
		
		
		Optional<Cliente> cliente1 = service.buscar(id);
				
		return ResponseEntity.ok().body(cliente1);
	}

}
