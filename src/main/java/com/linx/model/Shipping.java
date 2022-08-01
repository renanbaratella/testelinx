package com.linx.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_shipping")
public class Shipping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer amount;

	private String description;

	private String recipient_name;

	private String recipient_phone;

	@OneToOne
	private Address address;

	private String max_delivery_date;

	private String estimated_delivery_date;

	private String type;

	@OneToOne(mappedBy = "shipping", cascade = CascadeType.PERSIST)
	@JsonIgnoreProperties("shipping")
	private Order order;

	public Long getId() {
		return id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecipient_name() {
		return recipient_name;
	}

	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}

	public String getRecipient_phone() {
		return recipient_phone;
	}

	public void setRecipient_phone(String recipient_phone) {
		this.recipient_phone = recipient_phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getMax_delivery_date() {
		return max_delivery_date;
	}

	public void setMax_delivery_date(String max_delivery_date) {
		this.max_delivery_date = max_delivery_date;
	}

	public String getEstimated_delivery_date() {
		return estimated_delivery_date;
	}

	public void setEstimated_delivery_date(String estimated_delivery_date) {
		this.estimated_delivery_date = estimated_delivery_date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
