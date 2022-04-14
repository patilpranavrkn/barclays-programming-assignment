package com.tradestoreproj.barclays.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tradestoreproj.barclays.entity.TradeStore;
import com.tradestoreproj.barclays.model.TradeStoreRequestDTO;
@Component
public interface TradeOperationsService {

	public List<TradeStore> findAll();

	public void updateExpiryFlagOfTrade();

	public TradeStore saveNewTrade(TradeStoreRequestDTO newTradeDate);

	public void validateTrade(TradeStoreRequestDTO newTradeDate);


}
