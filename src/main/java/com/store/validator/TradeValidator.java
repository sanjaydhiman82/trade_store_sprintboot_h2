package com.store.validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.store.api.TradeRequest;
import com.store.entity.TradeEntity;
import com.store.exception.LowerMaturityException;
import com.store.exception.LowerVersionException;
import com.store.service.TradeService;
@Service
public class TradeValidator {
	@Autowired
	private TradeService service;
/***
 * 
 * @param requestReq
 * @return
 * @throws LowerVersionException
 * @throws LowerMaturityException
 */
	public boolean validation(TradeRequest requestReq) throws LowerVersionException, LowerMaturityException {
		
		List<TradeEntity> trades=service.findAllTradesByid(requestReq.getTradId());
		if(!CollectionUtils.isEmpty(trades)) {
			//During transmission if the lower version is being received by the store it will reject the trade and throw an exception. 
			//If the version is same it will override the existing record.
			Optional<TradeEntity> lowVersionExistOpt=trades.stream().filter(e-> requestReq.getVersion()<e.getVersion()).findFirst();
			if(lowVersionExistOpt.isPresent()) {
				throw new LowerVersionException("Lower version trade not allowed");
			}
			//2. Store should not allow the trade which has less maturity date then today date.
			if(requestReq.getMatruirtyDate().isBefore(LocalDate.now())) {
				throw new LowerMaturityException("Maturity date should not be less tehn today ");
			}		
		}
		return true;	
	}
}
