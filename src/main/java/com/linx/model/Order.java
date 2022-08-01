package com.linx.model;

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
import com.linx.enums.Status;

@Entity
@Table(name = "tb_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String currency;

	@Enumerated(EnumType.ORDINAL)
	private Status status;

	private String code;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Customer customer;

	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Shipping shipping;

	@OneToOne
	private Charge charge;

	@OneToOne(mappedBy = "order", cascade = CascadeType.PERSIST)
	private Item item;

	private Boolean closed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Status getStatus() {
		return status;
	}

	public Item getItem() {
		return item;
	}

	public void setItems(Item item) {
		this.item = item;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public Charge getCharge() {
		return charge;
	}

	public void setCharge(Charge charge) {
		this.charge = charge;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
