package com.aobfilho.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aobfilho.cursomc.domain.Estado;
import com.aobfilho.cursomc.service.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	

	@GetMapping("/{id}")
	public ResponseEntity<Estado> listar(@PathVariable Integer id) {
		Estado estado = estadoService.find(id);
		return ResponseEntity.ok().body(estado);		
	}
}
