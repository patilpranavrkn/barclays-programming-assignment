package com.tradestoreproj.barclays.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tradestoreproj.barclays.constant.StoreConstants;
import com.tradestoreproj.barclays.entity.TradeStore;
import com.tradestoreproj.barclays.exception.TradeOperationsCustomException;
import com.tradestoreproj.barclays.model.BaseApiResponse;
import com.tradestoreproj.barclays.model.ResponseListResponse;
import com.tradestoreproj.barclays.model.TradeStoreRequestDTO;
import com.tradestoreproj.barclays.service.TradeOperationsService;

@RestController
public class TradeStoreController {
	
	@Autowired
	TradeOperationsService tradeOperationService;
	/***
	 * 
	 * This endpoint retirieves all the information from the trade store
	 */
	
	@GetMapping("/trades")
	public ResponseEntity<ResponseListResponse> getAllProduct(){
		ResponseListResponse response =new ResponseListResponse();
		List<TradeStore>  data=tradeOperationService.findAll();
		response.setError(false);
		response.setStatusCode(StoreConstants.HTTP_OK_STATUS_CODE);
		response.setStatusMessage(StoreConstants.VALID_MESSAGE);
		response.setData(data);
		return ResponseEntity.ok().body(response);
	}
	
	/***
	 * This endpoint creates new Record in trade Store after the validations are completed
	 * @param newTradeStore
	 * @return
	 */
	
	@PostMapping("/createtrade")
	public ResponseEntity<BaseApiResponse> createTrade( @Valid @RequestBody TradeStoreRequestDTO newTradeStore){
		
		BaseApiResponse response =new BaseApiResponse();
		try {
			tradeOperationService.validateTrade(newTradeStore);
		} catch (TradeOperationsCustomException exp) {
			response.setError(true);
			response.setStatusCode(StoreConstants.HTTP_FORBIDDEN_STATUS_CODE);
			response.setStatusMessage(exp.getMessage());
			return ResponseEntity.status(403).body(response);
		}
		tradeOperationService.saveNewTrade(newTradeStore);
		response.setError(false);
		response.setStatusCode(StoreConstants.HTTP_OK_STATUS_CODE);
		response.setStatusMessage(StoreConstants.SUCCESS_TRADE_RECORD);
		return ResponseEntity.ok().body(response);
	}
	

}
