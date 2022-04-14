
package com.tradestoreproj.barclays.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TradeScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(TradeScheduledTasks.class);
	
	/**
	 * This method is scheduled to trigger automatically at every 60 seconds (1 minute). It will update the expiry flags based on the maturity date
	 */
	@Autowired
	TradeOperationsService tradeOperationService;
	@Scheduled(cron = "${trade.expiry.schedule}")
	public void updateExpiryFlagOfTrade() {
		log.info("Updating Flags at Time "+ LocalDateTime.now().toString());
		tradeOperationService.updateExpiryFlagOfTrade();
	}
}
