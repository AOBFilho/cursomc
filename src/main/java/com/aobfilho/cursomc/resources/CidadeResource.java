package com.aobfilho.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aobfilho.cursomc.domain.Cidade;
import com.aobfilho.cursomc.service.CidadeService;

@RestController
@RequestMapping(value="/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeService cidadeService;
	

	@GetMapping("/{id}")
	public ResponseEntity<Cidade> listar(@PathVariable Integer id) {
		Cidade cidade = cidadeService.find(id);
		return ResponseEntity.ok().body(cidade);		
	}
}
