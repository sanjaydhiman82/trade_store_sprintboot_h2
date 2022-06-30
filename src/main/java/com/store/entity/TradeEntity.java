package com.store.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "trade")
public class TradeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String tradId;
	private Long version;
	private String counterPartyId;
	private String bookId ;
	private LocalDate matruirtyDate;
	private LocalDate  createDate;
	@Column(length = 1)
	private char expired;

}
