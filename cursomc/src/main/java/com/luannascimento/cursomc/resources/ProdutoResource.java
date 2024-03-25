package com.luannascimento.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luannascimento.cursomc.domains.Produto;
import com.luannascimento.cursomc.dto.ProdutoDTO;
import com.luannascimento.cursomc.resources.utils.URL;
import com.luannascimento.cursomc.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Produto>> listar(@PathVariable("id") Integer id) {

		Optional<Produto> prod1 = service.buscar(id);

		return ResponseEntity.ok().body(prod1);
	}
	
	@GetMapping(value = "/paginar")
	public ResponseEntity<Page<ProdutoDTO>> listarPorPagina(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhaPorPagina", defaultValue = "24") Integer linhaPorPagina,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderarPor", defaultValue = "nome") String orderarPor) {
		
		
		Page<ProdutoDTO> catPaginada =  service.search(URL.decodeParam(nome), URL.decodeIntList(categorias), pagina, linhaPorPagina, orderarPor, direction);

		return ResponseEntity.ok().body(catPaginada);
	}


}
