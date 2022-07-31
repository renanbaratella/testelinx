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
	private CustomerRepository usuarioRepository;

	public Optional<Customer> cadastrarCustomer(Customer customer) {
		if (usuarioRepository.findByEmail(customer.getEmail()).isPresent())
			return Optional.empty();
		customer.setSenha(criptografarSenha(customer.getSenha()));
		return Optional.of(usuarioRepository.save(customer));
	}

	public Optional<Customer> atualizarCustomer(Customer customer) {
		if (usuarioRepository.findById(customer.getId()).isPresent()) {
			Optional<Customer> buscausuario = usuarioRepository.findByEmail(customer.getEmail());
			if (buscausuario.isPresent() && buscausuario.get().getId() != customer.getId()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuario já existe, null");
			}
			customer.setSenha(criptografarSenha(customer.getSenha()));
			return Optional.ofNullable(usuarioRepository.save(customer));
		}
		return Optional.empty();
	}

	public Optional<CustomerLogin> autenticarCustomer(Optional<CustomerLogin> customerLogin) {
		Optional<Customer> customer = usuarioRepository.findByEmail(customerLogin.get().getEmail());
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

	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
}