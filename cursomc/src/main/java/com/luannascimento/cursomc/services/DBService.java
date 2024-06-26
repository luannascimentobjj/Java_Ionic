package com.luannascimento.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luannascimento.cursomc.domains.Categoria;
import com.luannascimento.cursomc.domains.Cidade;
import com.luannascimento.cursomc.domains.Cliente;
import com.luannascimento.cursomc.domains.Endereco;
import com.luannascimento.cursomc.domains.Estado;
import com.luannascimento.cursomc.domains.ItemPedido;
import com.luannascimento.cursomc.domains.Pagamento;
import com.luannascimento.cursomc.domains.PagamentoComBoleto;
import com.luannascimento.cursomc.domains.PagamentoComCartao;
import com.luannascimento.cursomc.domains.Pedido;
import com.luannascimento.cursomc.domains.Produto;
import com.luannascimento.cursomc.domains.enums.EstadoPagamento;
import com.luannascimento.cursomc.domains.enums.Perfil;
import com.luannascimento.cursomc.domains.enums.TipoCliente;
import com.luannascimento.cursomc.repositories.CategoriaRepository;
import com.luannascimento.cursomc.repositories.CidadeRepository;
import com.luannascimento.cursomc.repositories.ClienteRepository;
import com.luannascimento.cursomc.repositories.EnderecoRepository;
import com.luannascimento.cursomc.repositories.EstadoRepository;
import com.luannascimento.cursomc.repositories.ItemPedidoRepository;
import com.luannascimento.cursomc.repositories.PagamentoRepository;
import com.luannascimento.cursomc.repositories.PedidoRepository;
import com.luannascimento.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired 
	private BCryptPasswordEncoder pe;
	
	public void instantiateLocalDatabese() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Eletronico");
		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
		Categoria cat4  = new Categoria(null, "Eletrônicos");
		Categoria cat5  = new Categoria(null, "Jardinagem");
		Categoria cat6  = new Categoria(null, "Decoração");
		Categoria cat7  = new Categoria(null, "Farmácia");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "São Paulo", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Luan Nascimento", "nascimento.hate@gmail.com", "13627927729", TipoCliente.PESSOAFISICA, pe.encode("senha"));
		cli1.getTelefones().addAll(Arrays.asList("1235679", "23423556"));
		
		Cliente cli2 = new Cliente(null, "Luan Oliveira", "nascimento.hate@hotmail.com", "99182772037", TipoCliente.PESSOAFISICA, pe.encode("senha"));
		cli2.addPerfil(Perfil.ADMIN);
		cli2.getTelefones().addAll(Arrays.asList("123567910", "23423576"));
		
		
		Endereco e1 = new Endereco(null, "Rua Flores", "32", "Casa1" , "Serra Grande", "24342767", c1, cli1);
		Endereco e2 = new Endereco(null, "Rua Pedras", "25", "Casa3" , "Serra Grande", "24342766", c2, cli1);
		Endereco e3 = new Endereco(null, "Rua Barros", "44", "Casa3" , "Serra Grande", "24342788", c2, cli2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepository.saveAllAndFlush(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAllAndFlush(Arrays.asList(e1, e2, e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2022 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2022 10:32"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2023 00:00"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAllAndFlush(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAllAndFlush(Arrays.asList(pgto1, pgto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip1, ip2));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		 itemPedidoRepository.saveAllAndFlush(Arrays.asList(ip1, ip2, ip3));
		
	}

}
