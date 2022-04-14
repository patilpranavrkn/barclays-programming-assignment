package com.tradestoreproj.barclays.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tradestoreproj.barclays.entity.TradeStore;
@Repository
public interface TradeStoreRepository extends JpaRepository<TradeStore,String>{

	@Query("select count(tradeId) from TradeStore where tradeId=?1 and version > ?2")
	int getcount(String tradeId,Integer version);
	
	@Transactional 
	@Modifying
	@Query("update TradeStore set expired='Y' where trunc(maturityDate) < trunc(CURRENT_DATE())")
	int updateExpiryFlagBasedOnMaturity();
	

}
