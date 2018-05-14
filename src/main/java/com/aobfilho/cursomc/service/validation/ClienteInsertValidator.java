package com.aobfilho.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.aobfilho.cursomc.domain.Cliente;
import com.aobfilho.cursomc.domain.enums.TipoCliente;
import com.aobfilho.cursomc.dto.ClienteNewDTO;
import com.aobfilho.cursomc.repositories.ClienteRepository;
import com.aobfilho.cursomc.resources.exceptions.FieldMessage;
import com.aobfilho.cursomc.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {};

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF é inválido"));
		}
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CNPJ é inválido"));
		}
		
		Cliente cliAux = clienteRepository.findByEmail(objDto.getEmail());
		if (cliAux != null) {
			list.add(new FieldMessage("email","Email já cadastrado"));
		}
		
		cliAux = clienteRepository.findByCpfOuCnpj(objDto.getCpfOuCnpj());
		if (cliAux != null) {
			if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo())) {
				list.add(new FieldMessage("cpfOuCnpj","CPF já cadastrado"));	
			}
			if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo())) {
				list.add(new FieldMessage("cpfOuCnpj","CNPJ já cadastrado"));	
			}
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
