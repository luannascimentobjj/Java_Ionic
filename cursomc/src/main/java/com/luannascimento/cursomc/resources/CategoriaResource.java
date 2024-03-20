package com.luannascimento.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luannascimento.cursomc.domain.Categoria;
import com.luannascimento.cursomc.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listar(@PathVariable("id") Integer id) {
		
		
		Optional<Categoria> cat1 = service.buscar(id);
				
		return ResponseEntity.ok().body(cat1);
	}

}
