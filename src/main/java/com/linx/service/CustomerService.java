package com.linx.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.linx.model.Customer;
import com.linx.model.CustomerLogin;
import com.linx.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Optional<Customer> cadastrarCustomer(Customer customer) {
		if (customerRepository.findByEmail(customer.getEmail()).isPresent())
			return Optional.empty();
		customer.setSenha(criptografarSenha(customer.getSenha()));
		return Optional.of(customerRepository.save(customer));
	}

	public Optional<Customer> atualizarCustomer(Customer customer) {
		if (customerRepository.findById(customer.getId()).isPresent()) {
			Optional<Customer> buscausuario = customerRepository.findByEmail(customer.getEmail());
			if (buscausuario.isPresent() && buscausuario.get().getId() != customer.getId()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario já existe, null");
			}
			
			customer.setSenha(criptografarSenha(customer.getSenha()));
			customer.setPhone(buscausuario.get().getPhone());
			customer.setAddress(buscausuario.get().getAddress());
			customer.setOrder(buscausuario.get().getOrder());
			customer.setCharge(buscausuario.get().getCharge());
	
			return Optional.ofNullable(customerRepository.save(customer));
		}
		return Optional.empty();
	}
	
	public Optional<Customer> patchCustomer(Customer customer) {
		Optional<Customer> customerBanco = customerRepository.findById(customer.getId());
		
		if(customer.getName() == null)customer.setName(customerBanco.get().getName());
		if(customer.getEmail() == null)customer.setEmail(customerBanco.get().getEmail());
		if(customer.getSenha() == null)customer.setSenha(criptografarSenha(customerBanco.get().getSenha()));
		if(customer.getPhone() == null)customer.setPhone(customerBanco.get().getPhone());
		if(customer.getDocument() == null)customer.setDocument(customerBanco.get().getDocument());
		if(customer.getDocument_type() == null)customer.setDocument_type(customerBanco.get().getDocument_type());
		if(customer.getType() == null)customer.setType(customerBanco.get().getType());
		if(customer.getGender() == null)customer.setGender(customerBanco.get().getGender());
		if(customer.getAddress() == null)customer.setAddress(customerBanco.get().getAddress());
		if(customer.getFb_id() == null)customer.setFb_id(customerBanco.get().getFb_id());
		if(customer.getFb_access_token() == null)customer.setFb_access_token(customerBanco.get().getFb_access_token());
		if(customer.getDelinquent() == null)customer.setDelinquent(customerBanco.get().getDelinquent());
		if(customer.getCode() == null)customer.setCode(customerBanco.get().getCode());
		if(customer.getBirthdate() == null)customer.setBirthdate(customerBanco.get().getBirthdate());
		if(customer.getOrder() == null)customer.setOrder(customerBanco.get().getOrder());
		if(customer.getCharge() == null)customer.setCharge(customerBanco.get().getCharge());
		
		return Optional.ofNullable(customerRepository.save(customer));
	
	}
	

	public Optional<CustomerLogin> autenticarCustomer(Optional<CustomerLogin> customerLogin) {
		Optional<Customer> customer = customerRepository.findByEmail(customerLogin.get().getEmail());
		if (customer.isPresent()) {
			if (compararSenhas(customerLogin.get().getSenha(), customer.get().getSenha())) {
				customerLogin.get().setId(customer.get().getId());
				customerLogin.get().setName(customer.get().getName());
				customerLogin.get().setToken(gerarBasicToken(customerLogin.get().getEmail(), customerLogin.get().getSenha()));
				return customerLogin;
			}
		}
		return Optional.empty();
	}

	private String gerarBasicToken(String usuario, String senha) {
		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);
	}

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);
	}

	public String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
}