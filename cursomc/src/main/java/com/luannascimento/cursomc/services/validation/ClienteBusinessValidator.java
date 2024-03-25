package com.luannascimento.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.luannascimento.cursomc.domains.Cliente;
import com.luannascimento.cursomc.domains.enums.TipoCliente;
import com.luannascimento.cursomc.dto.ClienteDTO;
import com.luannascimento.cursomc.exceptions.FieldMessage;
import com.luannascimento.cursomc.repositories.ClienteRepository;
import com.luannascimento.cursomc.services.validation.utils.isCpfOrCnpj;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ClienteBusinessValidator implements ConstraintValidator<ClienteBusiness, ClienteDTO>{
	
	
	@Autowired
	private ClienteRepository cliente;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteBusiness ann) {
		
	}

	
	
	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map =  (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId =  (map.get("id") == null) ? null : Integer.parseInt(map.get("id"));
	
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (clienteDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !isCpfOrCnpj.isValidCPF(clienteDTO.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF Inválido"));
		}
		
		if (clienteDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !isCpfOrCnpj.isValidCNPJ(clienteDTO.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPNJ Inválido"));
		}
		
		if (uriId != null) {
			
			Cliente aux = cliente.findByEmail(clienteDTO.getEmail());
			if(aux != null && !aux.getId().equals(uriId)) {
				list.add(new FieldMessage("email", "Email já existente!"));
			}
			
		}else {
			Cliente aux = cliente.findByEmail(clienteDTO.getEmail());
			if(aux != null) {
				list.add(new FieldMessage("email", "Email já existente!"));
			}
		}
	
		
		
		for (FieldMessage e : list) {
			
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
			
			
		}
		
		return list.isEmpty();
	}

}
