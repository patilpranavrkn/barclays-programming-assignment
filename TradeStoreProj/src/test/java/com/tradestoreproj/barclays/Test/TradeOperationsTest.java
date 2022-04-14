package com.tradestoreproj.barclays.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.tradestoreproj.barclays.DemoApplication;
import com.tradestoreproj.barclays.constant.StoreConstants;
import com.tradestoreproj.barclays.controller.TradeStoreController;
import com.tradestoreproj.barclays.model.TradeStoreRequestDTO;

@SpringBootTest
@SpringJUnitConfig(DemoApplication.class)
@ExtendWith(SpringExtension.class)
 class TradeOperationsTest {
	
	@Autowired
	TradeStoreController tradeStoreController;
	@Test
	@DisplayName("Create and Save trade Successfully")
	void createTradeSuccessfullTest()
	{
		TradeStoreRequestDTO sampleRecord1 = new TradeStoreRequestDTO();
		sampleRecord1.setBookingId("B1");
		sampleRecord1.setTradeId("T1");
		sampleRecord1.setMaturityDate(LocalDate.now().plusDays(2));
		sampleRecord1.setVersion(1);
		sampleRecord1.setExpired("N");
		sampleRecord1.setCounterPartyId("CP-1");
		assertEquals(StoreConstants.HTTP_OK_STATUS_CODE,tradeStoreController.createTrade(sampleRecord1).getBody().getStatusCode());
	}
	

	@Test
	@DisplayName("Record Creation not happened as Validation on Date failed")
	void createTradeFailfullvalidationTest()
	{
		TradeStoreRequestDTO sampleRecord1 = new TradeStoreRequestDTO();
		sampleRecord1.setBookingId("B2");
		sampleRecord1.setTradeId("T2");
		sampleRecord1.setMaturityDate(LocalDate.now().minusDays(1));
		sampleRecord1.setVersion(1);
		sampleRecord1.setExpired("N");
		sampleRecord1.setCounterPartyId("CP-2");
		assertEquals(StoreConstants.HTTP_FORBIDDEN_STATUS_CODE,tradeStoreController.createTrade(sampleRecord1).getBody().getStatusCode());
	}
	
	@Test
	@DisplayName("Record Creation not happened as Validation on Version failed")
	void createTradeFailfullVersionvalidationTest()
	{
		TradeStoreRequestDTO sampleRecord1 = new TradeStoreRequestDTO();
		sampleRecord1.setBookingId("B2");
		sampleRecord1.setTradeId("T2");
		sampleRecord1.setMaturityDate(LocalDate.now().plusDays(1));
		sampleRecord1.setVersion(1);
		sampleRecord1.setExpired("N");
		sampleRecord1.setCounterPartyId("CP-2");
		
		tradeStoreController.createTrade(sampleRecord1);
		
		TradeStoreRequestDTO sampleRecord2 = new TradeStoreRequestDTO();
		sampleRecord2.setBookingId("B2");
		sampleRecord2.setTradeId("T2");
		sampleRecord2.setMaturityDate(LocalDate.now().plusDays(1));
		sampleRecord2.setVersion(2);
		sampleRecord2.setExpired("N");
		sampleRecord2.setCounterPartyId("CP-2");
		
		tradeStoreController.createTrade(sampleRecord2);
		TradeStoreRequestDTO sampleRecord3 = new TradeStoreRequestDTO();
		sampleRecord3.setBookingId("B2");
		sampleRecord3.setTradeId("T2");
		sampleRecord3.setMaturityDate(LocalDate.now().plusDays(1));
		sampleRecord3.setVersion(1);
		sampleRecord3.setExpired("N");
		sampleRecord3.setCounterPartyId("CP-2");
		
		assertEquals(StoreConstants.HTTP_FORBIDDEN_STATUS_CODE,tradeStoreController.createTrade(sampleRecord3).getBody().getStatusCode());
		
		
	}
	
	@Test
	@DisplayName("Fetch All the Records available in Store")
	void fetchAllRecordsAvailableInStoreTest()
	{
		TradeStoreRequestDTO sampleRecord1 = new TradeStoreRequestDTO();
		sampleRecord1.setBookingId("B2");
		sampleRecord1.setTradeId("T2");
		sampleRecord1.setMaturityDate(LocalDate.now().plusDays(1));
		sampleRecord1.setVersion(1);
		sampleRecord1.setExpired("N");
		sampleRecord1.setCounterPartyId("CP-2");
		
		tradeStoreController.createTrade(sampleRecord1);
		
		TradeStoreRequestDTO sampleRecord2 = new TradeStoreRequestDTO();
		sampleRecord2.setBookingId("B2");
		sampleRecord2.setTradeId("T2");
		sampleRecord2.setMaturityDate(LocalDate.now().plusDays(1));
		sampleRecord2.setVersion(2);
		sampleRecord2.setExpired("N");
		sampleRecord2.setCounterPartyId("CP-2");
		
		tradeStoreController.createTrade(sampleRecord2);
		TradeStoreRequestDTO sampleRecord3 = new TradeStoreRequestDTO();
		sampleRecord3.setBookingId("B3");
		sampleRecord3.setTradeId("T3");
		sampleRecord3.setMaturityDate(LocalDate.now().plusDays(1));
		sampleRecord3.setVersion(1);
		sampleRecord3.setExpired("N");
		sampleRecord3.setCounterPartyId("CP-2");
		
		assertThat(tradeStoreController.getAllProduct().getBody().getData().size()>=1).isTrue();
		
		
	}
	

	


}
