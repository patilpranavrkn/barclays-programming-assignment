package com.tradestoreproj.barclays.model;

import java.time.LocalDate;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TradeStoreRequestDTO {
	
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getCounterPartyId() {
		return counterPartyId;
	}
	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public LocalDate getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
	@Id
	@NotBlank(message="TradeId can't be blank")
	@NotEmpty(message=" TradeId can't be empty")
	@NotNull(message=" TradeId can't be null")
	@Size(max=255)																			// the maximum size of tradeId can be 255 characters as h2 db has String of size 255 Chars
	private String tradeId;
	@NotNull(message="version can't be null")
	private Integer version;
	@NotBlank(message="counterPartyId can't be blank")
	@NotEmpty(message="counterPartyId can't be empty")
	@NotNull(message="counterPartyId can't be null")
	@Size(max=255)
	private String counterPartyId;
	@NotBlank(message="bookingId can't be blank")
	@NotEmpty(message="bookingId can't be empty")
	@NotNull(message="bookingId can't be null")
	@Size(max=255)
	private String bookingId;
	private LocalDate maturityDate;
	@NotBlank(message="Expired can't be blank")
	@NotEmpty(message="Expired can't be blank")
	@NotNull(message=" Expired can't be null")
	@Size(max=1,min=1)
	@Pattern(regexp="^(?:Y|N)$",message="expired flag can be Y or N only")					// assumming expiry flag of trade can be Y or N only and adding this validation
	private String expired;

}


