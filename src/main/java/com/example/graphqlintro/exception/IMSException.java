package com.example.graphqlintro.exception;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.example.graphqlintro.dto.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class IMSException extends RuntimeException implements GraphQLError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2539316994292390511L;
	private ErrorResponse errorRespone;

	public IMSException(HttpStatus status, String errorDescr) {
		super();
		this.errorRespone = new ErrorResponse(status, errorDescr);
	}

	public IMSException(HttpStatus status, String errorDescr, int reasonCode) {
		super();
		this.errorRespone = new ErrorResponse(status, errorDescr, reasonCode);
	}

	@Override
	public String getMessage() {
		return this.errorRespone.toString();
	}

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		return null;
	}

	@Override
	public Map<String, Object> getExtensions() {
		return Collections.singletonMap("Error", this.errorRespone);
	}

	@Override
	@JsonIgnore
	public StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}

}
