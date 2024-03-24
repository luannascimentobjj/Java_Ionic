package com.luannascimento.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luannascimento.cursomc.domains.Categoria;
import com.luannascimento.cursomc.dto.CategoriaDTO;
import com.luannascimento.cursomc.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Categoria>> listar(@PathVariable("id") Integer id) {

		Optional<Categoria> cat1 = service.buscar(id);

		return ResponseEntity.ok().body(cat1);
	}

	@PostMapping
	public ResponseEntity<Void> inserir(@RequestBody Categoria categoria) {

		categoria = service.inserir(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable("id") Integer id, @RequestBody Categoria categoria) {

		categoria = service.atualizar(id, categoria);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {

		service.deletar(id);
		return ResponseEntity.noContent().build();

	}

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> listarTodos() {

		List<CategoriaDTO> cat1 = service.buscarTodos();

		return ResponseEntity.ok().body(cat1);
	}

}
