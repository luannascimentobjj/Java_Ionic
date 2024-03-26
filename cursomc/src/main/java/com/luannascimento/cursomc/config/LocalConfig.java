package com.luannascimento.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.luannascimento.cursomc.services.DBService;

@Configuration
@Profile("local")
public class LocalConfig {
	
	@Autowired
	private DBService dbService;

    @Bean
    boolean instantiateDatabase() throws ParseException {
		
		dbService.instantiateLocalDatabese();
		return true;
	
	}

}
