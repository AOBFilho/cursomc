package com.aobfilho.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aobfilho.cursomc.domain.Categoria;
import com.aobfilho.cursomc.service.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	

	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		Optional<Categoria> cat = categoriaService.buscar(id);
		if (!cat.isPresent()) {
			return ResponseEntity.noContent().build();
		};
		return ResponseEntity.ok().body(cat.get());
	}
}
