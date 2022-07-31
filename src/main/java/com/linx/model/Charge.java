package com.linx.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_charge")
public class Charge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;

	private String gateway_id;

	private Integer amount;

	private String payment_method;

	private String status;

	private LocalDate due_at;

	@OneToOne
	@JsonIgnoreProperties("charge")
	private Customer customer;

	@OneToOne(mappedBy = "charge", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "charge", "shipping", "order" })
	private Order order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGateway_id() {
		return gateway_id;
	}

	public void setGateway_id(String gateway_id) {
		this.gateway_id = gateway_id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDue_at() {
		return due_at;
	}

	public void setDue_at(LocalDate due_at) {
		this.due_at = due_at;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
