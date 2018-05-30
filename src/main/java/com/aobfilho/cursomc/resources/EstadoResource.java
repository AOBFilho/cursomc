package com.aobfilho.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aobfilho.cursomc.domain.Cidade;
import com.aobfilho.cursomc.domain.Estado;
import com.aobfilho.cursomc.dto.CidadeDTO;
import com.aobfilho.cursomc.dto.EstadoDTO;
import com.aobfilho.cursomc.service.CidadeService;
import com.aobfilho.cursomc.service.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	

	@GetMapping("/{id}")
	public ResponseEntity<Estado> find(@PathVariable Integer id) {
		Estado estado = estadoService.find(id);
		return ResponseEntity.ok().body(estado);		
	}
	
	@GetMapping()
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> lista = estadoService.findAll();
		List<EstadoDTO> listaDTO = lista.stream().map(x -> new EstadoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}
	
	
	@GetMapping("/{id}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer id){
		List<Cidade> list = cidadeService.findByEstado(id);
		List<CidadeDTO> listDTO = list.stream().map(x -> new CidadeDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
