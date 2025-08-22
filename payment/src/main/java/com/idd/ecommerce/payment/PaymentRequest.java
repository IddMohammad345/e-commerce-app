package com.idd.ecommerce.payment;

import java.math.BigDecimal;

import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;

public record PaymentRequest(
		Integer id,
		BigDecimal amount,
		PaymentMethod paymentMethod,
		Integer orderId,
		String OrderReference,
		Customer customer
		) {

}
