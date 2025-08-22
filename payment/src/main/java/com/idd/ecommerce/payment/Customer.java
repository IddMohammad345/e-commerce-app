package com.idd.ecommerce.payment;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Validated
public record Customer(
		String id,
		@NotNull(message = "Firstname is required")
		String firstname,
		@NotNull(message = "Lastname is required")
		String lastname,
		@NotNull(message = "Email is required")
		@Email(message = "The customer is not correctly formatted")
		String email
		) {

}
