package com.linx.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.linx.enums.DocumentType;
import com.linx.enums.Type;

@Entity
@Table(name = "tb_customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private String name;

	private String email;

	private String senha;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties({"customer", "phone"})
	private Phone phone;

	private String document;

	@Enumerated(EnumType.ORDINAL)
	private DocumentType document_type;

	@Enumerated(EnumType.ORDINAL)
	private Type type;

	private String gender;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties({"customer", "address", "shipping"})
	private Address address;

	private Integer fb_id;

	private String fb_access_token;

	private Boolean delinquent;

	private String code;

	private LocalDate birthdate;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties({"customer", "charge"})
	private Order order;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties({"customer", "order"})
	private Charge charge;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public DocumentType getDocument_type() {
		return document_type;
	}

	public void setDocument_type(DocumentType document_type) {
		this.document_type = document_type;
	}

	public Type getType() {
		return type;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getFb_id() {
		return fb_id;
	}

	public void setFb_id(Integer fb_id) {
		this.fb_id = fb_id;
	}

	public String getFb_access_token() {
		return fb_access_token;
	}

	public void setFb_access_token(String fb_access_token) {
		this.fb_access_token = fb_access_token;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Boolean getDelinquent() {
		return delinquent;
	}

	public void setDelinquent(Boolean delinquent) {
		this.delinquent = delinquent;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Charge getCharge() {
		return charge;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
	}

}
