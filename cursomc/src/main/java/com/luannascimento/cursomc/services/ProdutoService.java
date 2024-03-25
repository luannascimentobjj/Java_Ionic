package com.luannascimento.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.luannascimento.cursomc.domains.Categoria;
import com.luannascimento.cursomc.domains.Produto;
import com.luannascimento.cursomc.dto.ProdutoDTO;
import com.luannascimento.cursomc.exceptions.ObjectNotFoundException;
import com.luannascimento.cursomc.repositories.CategoriaRepository;
import com.luannascimento.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Optional<Produto> buscar(Integer id) {

		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo" + Produto.class.getName());
		}

		return produto;

	}
	
	public Page<ProdutoDTO> search(String nome, List<Integer> ids, Integer pagina, Integer linhaPorPagina, String orderarPor, String direction) {

		PageRequest pageRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(direction), orderarPor);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
				
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest).map(obj -> new ProdutoDTO(obj)); 
		
		
	}

}
