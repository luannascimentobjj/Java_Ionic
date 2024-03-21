package com.luannascimento.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luannascimento.cursomc.domains.Categoria;
import com.luannascimento.cursomc.domains.Cidade;
import com.luannascimento.cursomc.domains.Cliente;
import com.luannascimento.cursomc.domains.Endereco;
import com.luannascimento.cursomc.domains.Estado;
import com.luannascimento.cursomc.domains.Produto;
import com.luannascimento.cursomc.domains.enums.TipoCliente;
import com.luannascimento.cursomc.repositories.CategoriaRepository;
import com.luannascimento.cursomc.repositories.CidadeRepository;
import com.luannascimento.cursomc.repositories.ClienteRepository;
import com.luannascimento.cursomc.repositories.EnderecoRepository;
import com.luannascimento.cursomc.repositories.EstadoRepository;
import com.luannascimento.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepositoy;
	
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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Eletronico");
		
		Produto p1 = new Produto(null, "Computador", 2500.00);
		Produto p2 = new Produto(null, "Placa de Video", 1500.00);
		Produto p3 = new Produto(null, "Memoria", 500.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepositoy.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "São Paulo", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "13627927729", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("1235679", "23423556"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "32", "Casa1" , "Serra Grande", "24342767", c1, cli1);
		Endereco e2 = new Endereco(null, "Rua Pedras", "25", "Casa3" , "Serra Grande", "24342766", c2, cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAllAndFlush(Arrays.asList(cli1));
		enderecoRepository.saveAllAndFlush(Arrays.asList(e1, e2));
		
	}
	

}
