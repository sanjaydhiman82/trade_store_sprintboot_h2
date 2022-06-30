package com.store.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.entity.TradeEntity;
import com.store.exception.LowerMaturityException;
import com.store.exception.LowerVersionException;
import com.store.service.TradeService;
import com.store.validator.TradeValidator;

@RestController
@RequestMapping("api/v1")
public class TradeController {
	@Autowired
	TradeValidator validatiorService;
	@Autowired
	TradeService service;
	
	@PostMapping(value="/trade",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
	public TradeResponse saveTrade(@RequestBody TradeRequest request) throws LowerVersionException, LowerMaturityException{
		validatiorService.validation(request);
		return prepareResponse(service.saveTrade(request));
	}
	/***
	 * 
	 * @param entity
	 * @return
	 */
	private TradeResponse prepareResponse(TradeEntity entity){
		return TradeResponse.builder()
				.bookId(entity.getBookId())
				.tradId(entity.getTradId())
				.version(entity.getVersion())
				.counterPartyId(entity.getCounterPartyId())
				.createDate(entity.getCreateDate())
				.expired(entity.getExpired())
				.matruirtyDate(entity.getMatruirtyDate())
				.build();
	}
}
