package com.aobfilho.cursomc.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.aobfilho.cursomc.domain.Endereco;
import com.aobfilho.cursomc.domain.ItemPedido;
import com.aobfilho.cursomc.domain.Pagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PedidoDTO {

	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	private Pagamento pagamento;
	private ClienteDTO cliente;
	private Endereco enderecoDeEntrega;
	
	private Set<ItemPedido> itens = new HashSet<ItemPedido>();

	public PedidoDTO(Integer id, Date instante, Pagamento pagamento, ClienteDTO cliente, Endereco enderecoDeEntrega,
			Set<ItemPedido> itens) {
		super();
		this.id = id;
		this.instante = instante;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.itens = itens;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
}
