package com.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.entity.TradeEntity;
@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Long>{
	List<TradeEntity> findByTradId(String tradeId);
	 
}
