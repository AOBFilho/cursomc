package com.aobfilho.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aobfilho.cursomc.domain.Produto;
import com.aobfilho.cursomc.dto.ProdutoDTO;
import com.aobfilho.cursomc.resources.utils.URL;
import com.aobfilho.cursomc.service.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	

	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = produtoService.find(id);
		return ResponseEntity.ok().body(obj);		
	}
	
	
	@GetMapping()
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(name="nome", defaultValue="") String nome,
			@RequestParam(name="categorias", defaultValue="") String categorias,
			@RequestParam(name="page", defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(name="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(name="direction", defaultValue="ASC") String direction){
		
		List<Integer> ids = URL.decodeIntList(categorias);
		String nomeDecoded = URL.decodeParam(nome);
		Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	
}
