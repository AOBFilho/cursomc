package com.aobfilho.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aobfilho.cursomc.domain.Categoria;
import com.aobfilho.cursomc.domain.Cliente;
import com.aobfilho.cursomc.domain.Produto;
import com.aobfilho.cursomc.repositories.CategoriaRepository;
import com.aobfilho.cursomc.repositories.ProdutoRepository;
import com.aobfilho.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+
				", Tipo: "+Cliente.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage,
			String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids); 
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);
	}
}
