package com.stackroute.keepnote.dto;

import java.util.ArrayList;
import java.util.List;

import com.stackroute.keepnote.model.LoanDto;

public class LoanDtoList {

	List<LoanDto> loanDto=new ArrayList<>();

	public List<LoanDto> getLoanDto() {
		return loanDto;
	}

	public void setLoanDto(List<LoanDto> loanDto) {
		this.loanDto = loanDto;
	}
	
}
