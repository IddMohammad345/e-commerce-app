package com.idd.ecommerce.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.idd.ecommerce.customer.CustomerResponse;
import com.idd.ecommerce.order.PaymentMethod;
import com.idd.ecommerce.product.PurchaseResponse;

public record OrderConfirmation(
		String orderReference,
		BigDecimal totalAmount,
		PaymentMethod paymentMethod,
		CustomerResponse customer,
		List<PurchaseResponse>products
		) {

}
