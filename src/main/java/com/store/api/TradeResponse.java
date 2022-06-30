package com.store.api;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class TradeResponse {
	private String tradId;
	private Long version;
	private String counterPartyId;
	private String bookId ;
	private LocalDate matruirtyDate;
	private LocalDate  createDate;
	private char expired;
	

}
