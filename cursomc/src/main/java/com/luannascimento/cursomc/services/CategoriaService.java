package com.luannascimento.cursomc.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luannascimento.cursomc.domains.Categoria;
import com.luannascimento.cursomc.exceptions.ObjectNotFoundException;
import com.luannascimento.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Optional<Categoria> buscar(Integer id) {
		
		Optional<Categoria> categoriaObj = repo.findById(id);
		
		if(categoriaObj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo"+ Categoria.class.getName());
		}
				
		return categoriaObj;
		
	}
	
}
