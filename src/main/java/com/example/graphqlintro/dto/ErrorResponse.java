package com.example.graphqlintro.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse {

	private HttpStatus errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	private String errorDescr;

	private int reasonCode;

	public ErrorResponse(HttpStatus errorStatus, String errorDescr) {
		super();
		this.errorStatus = errorStatus;
		this.errorDescr = errorDescr;
	}

}
