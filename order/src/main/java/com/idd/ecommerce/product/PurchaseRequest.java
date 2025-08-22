package com.idd.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
		@NotNull(message = "Product is manadatory")
		Integer productId,
		@Positive(message = "Quantity is mandatory")
		double quantity
		) {

}
