package com.tradestoreproj.barclays.serviceimpl;


import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradestoreproj.barclays.entity.TradeStore;
import com.tradestoreproj.barclays.exception.TradeOperationsCustomException;
import com.tradestoreproj.barclays.model.TradeStoreRequestDTO;
import com.tradestoreproj.barclays.repository.TradeStoreRepository;
import com.tradestoreproj.barclays.service.TradeOperationsService;
@Service
public class TradeOperationsServiceImpl  implements TradeOperationsService{
	
	private static final Logger log = LoggerFactory.getLogger(TradeOperationsServiceImpl.class);

	@Autowired
	TradeStoreRepository tradeStoreRepository;

	public List<TradeStore> findAll() {
		return tradeStoreRepository.findAll();
	}


	private boolean validateMaturityOfDates(TradeStoreRequestDTO newTradeDate) {
		return !newTradeDate.getMaturityDate().isBefore(LocalDate.now());
	}
	/***
	 * this method validates the new TradeRecord and respective exceptions are thrown if validations fail
	 * USE-CASE 1.Store should not allow the trade which has less maturity date then today date.
		2. During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.

	 */
	public void validateTrade(TradeStoreRequestDTO newTradeDate) {
		if (!validateMaturityOfDates(newTradeDate)) {
			log.info("Cannot Proceed with the Trade as Maturity Date is less than today Date for Trade ID :"
					+ newTradeDate.getTradeId());
			throw new TradeOperationsCustomException(
					"Cannot Proceed with the Trade as Maturity Date is less than today Date for Trade ID :"
							+ newTradeDate.getTradeId());
		}
		int count = tradeStoreRepository.getcount(newTradeDate.getTradeId(), newTradeDate.getVersion());
		if (count > 0) {
			log.info("Invalid Version of Trade Recieved for trade with ID:" + newTradeDate.getTradeId());
			throw new TradeOperationsCustomException(
					"Invalid Version of Trade Recieved for trade with ID:" + newTradeDate.getTradeId());
		}
	}
	/***
	 * The below method Updates the expiry flags based on the maturity date and gets called after specific time as it is scheduled
	 * USE CASE Store should automatically update the expire flag if in a store the trade crosses the maturity date.

	 */
	public void updateExpiryFlagOfTrade() {
		log.info("Updating Expiry Flags of Trades based on Maturity Date");
		tradeStoreRepository.updateExpiryFlagBasedOnMaturity();
	}

	public TradeStore saveNewTrade(TradeStoreRequestDTO newTradeDate) {
		
		return tradeStoreRepository.save(getEntity(newTradeDate));
	}
	private TradeStore  getEntity(TradeStoreRequestDTO newTradeDate)
	{
		TradeStore tradeStore=new TradeStore();
		tradeStore.setBookingId(newTradeDate.getBookingId());
		tradeStore.setCounterPartyId(newTradeDate.getCounterPartyId());
		tradeStore.setExpired(newTradeDate.getExpired());
		tradeStore.setTradeId(newTradeDate.getTradeId());
		tradeStore.setVersion(newTradeDate.getVersion());
		tradeStore.setMaturityDate(newTradeDate.getMaturityDate());
		tradeStore.setCreatedDate(LocalDate.now());						//Setting todays date as Created Date
		
		return tradeStore;
	}
    

}
