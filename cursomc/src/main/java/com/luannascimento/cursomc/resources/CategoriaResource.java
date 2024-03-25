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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.Page;

import com.luannascimento.cursomc.domains.Categoria;
import com.luannascimento.cursomc.dto.CategoriaDTO;
import com.luannascimento.cursomc.services.CategoriaService;

import jakarta.validation.Valid;

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
	public ResponseEntity<Void> inserir(@Valid @RequestBody CategoriaDTO categoriaDTO) {

		Categoria categoria = service.inserir(service.convertFromDTO(categoriaDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar( @PathVariable("id") Integer id, @Valid @RequestBody CategoriaDTO categoriaDTO) {

		service.atualizar(service.convertFromDTO(id, categoriaDTO));
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

	@GetMapping(value= "/paginar")
	public ResponseEntity<Page<CategoriaDTO>> listarPorPagina(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhaPorPagina", defaultValue = "24") Integer linhaPorPagina,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderarPor", defaultValue = "nome") String orderarPor) {

		 Page<CategoriaDTO> catPaginada =  service.buscaPaginada(pagina, linhaPorPagina, orderarPor, direction);

		return ResponseEntity.ok().body(catPaginada);
	}

}
