package com.idd.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

	public Order toOrder( OrderRequest request) {
		return Order.builder()
				.id(request.id())
				.customerId(request.customerId())
				.reference(request.reference())
				.totalAmount(request.amount())
				.paymentMedhod(request.paymentMethod())
				.build();
	}
	
	public OrderResponse fromOrder(Order order) {
		return new OrderResponse(
				order.getId(),
				order.getReference(),
				order.getTotalAmount(),
				order.getPaymentMedhod(),
				order.getCustomerId());
	}

}
