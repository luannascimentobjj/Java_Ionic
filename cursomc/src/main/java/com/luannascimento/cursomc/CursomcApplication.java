package com.luannascimento.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luannascimento.cursomc.domain.Categoria;
import com.luannascimento.cursomc.repositories.CategoriaRepository;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepositoy;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Banco de Dados");
		Categoria cat2 = new Categoria(null, "Java");
		
		categoriaRepositoy.saveAll(Arrays.asList(cat1, cat2));
		
	}

}
