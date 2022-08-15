package com.example.demo.payload;

import java.io.Serializable;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "success", "message" })
public class ApiResponse implements Serializable {
	@JsonIgnore
	private static final long serialVersionUID = 1129390948168378619L;
	@JsonProperty
	private Boolean success;
	@JsonProperty
	private String message;
	@JsonIgnore
	private HttpStatus status;

	public ApiResponse(Boolean success, String message, HttpStatus status) {
		super();
		this.success = success;
		this.message = message;
		this.status = status;
	}

	public ApiResponse(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public ApiResponse() {
		super();
	}

}
