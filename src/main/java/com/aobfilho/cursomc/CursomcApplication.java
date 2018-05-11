package com.aobfilho.cursomc;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aobfilho.cursomc.domain.Categoria;
import com.aobfilho.cursomc.domain.Cidade;
import com.aobfilho.cursomc.domain.Cliente;
import com.aobfilho.cursomc.domain.Endereco;
import com.aobfilho.cursomc.domain.Estado;
import com.aobfilho.cursomc.domain.ItemPedido;
import com.aobfilho.cursomc.domain.Pagamento;
import com.aobfilho.cursomc.domain.PagamentoComBoleto;
import com.aobfilho.cursomc.domain.PagamentoComCartao;
import com.aobfilho.cursomc.domain.Pedido;
import com.aobfilho.cursomc.domain.Produto;
import com.aobfilho.cursomc.domain.enums.EstadoPagamento;
import com.aobfilho.cursomc.domain.enums.TipoCliente;
import com.aobfilho.cursomc.repositories.CategoriaRepository;
import com.aobfilho.cursomc.repositories.CidadeRepository;
import com.aobfilho.cursomc.repositories.ClienteRepository;
import com.aobfilho.cursomc.repositories.EnderecoRepository;
import com.aobfilho.cursomc.repositories.EstadoRepository;
import com.aobfilho.cursomc.repositories.ItemPedidoRepository;
import com.aobfilho.cursomc.repositories.PagamentoRepository;
import com.aobfilho.cursomc.repositories.PedidoRepository;
import com.aobfilho.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository; 
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	private Categoria cat1;
	private Categoria cat2;
	private Categoria cat3;
	private Categoria cat4;
	private Categoria cat5;
	private Categoria cat6;
	private Categoria cat7;
	private Produto p1;
	private Produto p2;
	private Produto p3;
	private Estado est1;
	private Estado est2;
	private Cidade c1;
	private Cidade c2;
	private Cidade c3;
	private Cliente cli1;
	private Endereco e1;
	private Endereco e2;
	private Pedido ped1;
	private Pedido ped2;
	private Pagamento pagto1;
	private Pagamento pagto2;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		adicionarDadosDeTeste();
	}

	private void adicionarDadosDeTeste() {
		adicionarCategoriaProduto();
		adicionarEstadoCidade();
		adiconarClienteEndereco();
		adicionarPedidoPagamento();
		adicionarItemPedido();
	}	
	
	private void adicionarCategoriaProduto() {
		cat1 = new Categoria(null,"Informática");
		cat2 = new Categoria(null,"Escritório");
		cat3 = new Categoria(null,"Cama, mesa e banho");
		cat4 = new Categoria(null,"Eletrônicos");
		cat5 = new Categoria(null,"Jardinagem");
		cat6 = new Categoria(null,"Decoração");
		cat7 = new Categoria(null,"Perfumaria");

		
		p1 = new Produto(null,"Computador",BigDecimal.valueOf(2000.00));
		p2 = new Produto(null,"Impressora",BigDecimal.valueOf(800.00));
		p3 = new Produto(null,"Mouse",BigDecimal.valueOf(80.00));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().add(cat1);
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	}
	
	private void adicionarEstadoCidade() {
		est1 = new Estado(null,"Minas Gerais");
		est2 = new Estado(null,"São Paulo");
		
		c1 = new Cidade(null,"Uberlândia",est1);
		c2 = new Cidade(null,"São Paulo",est2);
		c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));		
	}	
	
	private void adiconarClienteEndereco() {
		cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		e1 = new Endereco(null,"Rua Flores","300","Apto 203","Jardim","38220834",cli1,c1);
		e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",cli1,c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}
	
	private void adicionarPedidoPagamento() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		try {			
			ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
			ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
			
			pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
			ped1.setPagamento(pagto1);
			pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
			ped2.setPagamento(pagto2);
			
			cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
			
			pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
			pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void adicionarItemPedido() {
		ItemPedido ip1 = new ItemPedido(ped1, p1, BigDecimal.valueOf(0.00), BigDecimal.valueOf(1), BigDecimal.valueOf(2000.00));
		ItemPedido ip2 = new ItemPedido(ped1, p3, BigDecimal.valueOf(0.00), BigDecimal.valueOf(2), BigDecimal.valueOf(80.00));
		ItemPedido ip3 = new ItemPedido(ped2, p2, BigDecimal.valueOf(100.00), BigDecimal.valueOf(1), BigDecimal.valueOf(800.00));
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
	
}
