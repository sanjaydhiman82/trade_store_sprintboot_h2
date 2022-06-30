package com.store.api;

import java.time.LocalDate;

import lombok.Data;
@Data
public class TradeRequest {
	private String tradId;
	private Long version;
	private String counterPartyId;
	private String bookId ;
	private LocalDate matruirtyDate;

}
