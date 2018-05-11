package com.aobfilho.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aobfilho.cursomc.domain.Cliente;
import com.aobfilho.cursomc.domain.Pedido;
import com.aobfilho.cursomc.dto.ClienteDTO;
import com.aobfilho.cursomc.dto.PedidoDTO;
import com.aobfilho.cursomc.repositories.PedidoRepository;
import com.aobfilho.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public PedidoDTO find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);

		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
		
		Pedido ped = obj.get();
		Cliente cli = ped.getCliente();
		ClienteDTO clienteDTO = new ClienteDTO(cli.getId(), cli.getNome(),cli.getEmail(), cli.getCpfOuCnpj(), cli.getTipo().getCodigo(),cli.getTelefones());
		PedidoDTO pedDTO = new PedidoDTO(ped.getId(), ped.getInstante(), ped.getPagamento(),clienteDTO, ped.getEnderecoDeEntrega(), ped.getItens());

		return pedDTO;
	}
}
