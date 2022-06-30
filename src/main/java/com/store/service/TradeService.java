package com.store.service;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.store.api.TradeRequest;
import com.store.entity.TradeEntity;
import com.store.repository.TradeRepository;
@Service
public class TradeService {
	@Autowired
	private TradeRepository repository;
	private Logger log=LoggerFactory.getLogger(TradeService.class);
	public TradeEntity saveTrade(@RequestBody TradeRequest request){
		
		try{
			log.info("Entering saveTrade");
			return repository.saveAndFlush(getTradeEntity(request));	
		}catch(ConstraintViolationException e) {
			log.info("Entering saveTrade");
		}
		return null;
	}
	
	private TradeEntity getTradeEntity(TradeRequest request) {
		TradeEntity entity=new TradeEntity();
		entity.setBookId(request.getBookId());
		entity.setCounterPartyId(request.getCounterPartyId());
		entity.setCreateDate(LocalDate.now());
		//Store should automatically update the expire flag if in a store the trade crosses the maturity date.
		entity.setExpired((LocalDate.now().isAfter(request.getMatruirtyDate()))?'Y':'N');
		entity.setMatruirtyDate(request.getMatruirtyDate());
		entity.setTradId(request.getTradId());
		entity.setVersion(request.getVersion());
		return entity;
	}

	public List<TradeEntity> findAllTradesByid(String tradId) {
		return repository.findByTradId(tradId);
		
	}

}
