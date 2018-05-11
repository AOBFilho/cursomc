package com.aobfilho.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aobfilho.cursomc.dto.PedidoDTO;
import com.aobfilho.cursomc.service.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDTO> listar(@PathVariable Integer id) {
		PedidoDTO pedido = pedidoService.find(id);
		return ResponseEntity.ok().body(pedido);		
	}
}
