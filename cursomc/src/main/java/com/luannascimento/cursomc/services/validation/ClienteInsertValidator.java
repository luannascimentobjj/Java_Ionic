package com.luannascimento.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.luannascimento.cursomc.domains.Cliente;
import com.luannascimento.cursomc.domains.enums.TipoCliente;
import com.luannascimento.cursomc.dto.ClienteDTO;
import com.luannascimento.cursomc.exceptions.FieldMessage;
import com.luannascimento.cursomc.repositories.ClienteRepository;
import com.luannascimento.cursomc.services.validation.utils.isCpfOrCnpj;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDTO>{
	
	
	@Autowired
	private ClienteRepository cliente;
	
	
	@Override
	public void initialize(ClienteInsert ann) {
		
	}

	
	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (clienteDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !isCpfOrCnpj.isValidCPF(clienteDTO.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF Inválido"));
		}
		
		if (clienteDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !isCpfOrCnpj.isValidCNPJ(clienteDTO.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPNJ Inválido"));
		}
		
		
		Cliente aux = cliente.findByEmail(clienteDTO.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente!"));
		}
		
		
		for (FieldMessage e : list) {
			
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
			
			
		}
		
		return list.isEmpty();
	}

}
