package com.luannascimento.cursomc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.luannascimento.cursomc.domains.Categoria;
import com.luannascimento.cursomc.dto.CategoriaDTO;
import com.luannascimento.cursomc.exceptions.DataIntegrityException;
import com.luannascimento.cursomc.exceptions.ObjectNotFoundException;
import com.luannascimento.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Optional<Categoria> buscar(Integer id) {

		Optional<Categoria> categoria = categoriaRepository.findById(id);

		if (categoria.isEmpty()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo" + Categoria.class.getName());
		}

		return categoria;

	}

	public Categoria inserir(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);

	}

	public void atualizar(Categoria categoria) {
		buscar(categoria.getId());
		categoriaRepository.save(categoria);

	}

	public void deletar(Integer id) {

		buscar(id);
		try {

			categoriaRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}

	}

	public List<CategoriaDTO> buscarTodos() {

		List<Categoria> listaCategoria = categoriaRepository.findAll();
		List<CategoriaDTO> listDTO = listaCategoria.stream().map(obj -> new CategoriaDTO(obj))
				.collect(Collectors.toList());

		if (listDTO.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado!");
		}

		return listDTO;

	}

	public Page<CategoriaDTO> buscaPaginada(Integer pagina, Integer linhaPorPagina, String orderarPor, String direction) {

		PageRequest pageRequest = PageRequest.of(pagina, linhaPorPagina, Direction.valueOf(direction), orderarPor);
		Page<CategoriaDTO> listDTO = categoriaRepository.findAll(pageRequest).map(obj -> new CategoriaDTO(obj));

		return listDTO;

	}
	
	public Categoria convertFromDTO (Integer id, CategoriaDTO dto) {
		return new Categoria(id, dto.getNome());
	}
	
	public Categoria convertFromDTO (CategoriaDTO dto) {
		return new Categoria(dto.getNome());
	}

}
