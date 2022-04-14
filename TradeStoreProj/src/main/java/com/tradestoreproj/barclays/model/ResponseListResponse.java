package com.tradestoreproj.barclays.model;

import java.util.List;

import com.tradestoreproj.barclays.entity.TradeStore;

public class ResponseListResponse extends BaseApiResponse {
	
	 public List<TradeStore> getData() {
		return data;
	}

	public void setData(List<TradeStore> data) {
		this.data = data;
	}

	List<TradeStore> data;

}
