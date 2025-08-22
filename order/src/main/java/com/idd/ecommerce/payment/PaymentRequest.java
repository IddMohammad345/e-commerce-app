package com.idd.ecommerce.payment;

import java.math.BigDecimal;

import com.idd.ecommerce.customer.CustomerResponse;
import com.idd.ecommerce.order.PaymentMethod;

public record PaymentRequest(
		BigDecimal amount,
		PaymentMethod paymentMethod,
		Integer orderId,
		String OrderReference,
		CustomerResponse customer
		) {

}
