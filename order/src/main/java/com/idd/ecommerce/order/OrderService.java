package com.idd.ecommerce.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.idd.ecommerce.customer.CustomerClient;
import com.idd.ecommerce.exception.BusinessException;
import com.idd.ecommerce.kafka.OrderConfirmation;
import com.idd.ecommerce.kafka.OrderProducer;
import com.idd.ecommerce.orderline.OrderLineRequest;
import com.idd.ecommerce.orderline.OrderLineService;
import com.idd.ecommerce.payment.PaymentClient;
import com.idd.ecommerce.payment.PaymentRequest;
import com.idd.ecommerce.product.ProductClient;
import com.idd.ecommerce.product.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository repository;
	private final CustomerClient customerClinet;
	private final ProductClient productClint;
	private final OrderMapper mapper;
	private final OrderLineService orderLineService;
	private final OrderProducer orderProducer;
	private final PaymentClient paymentClient;
	
	public Integer createdOrder(OrderRequest request) {
		// check the customer
		var customer = this.customerClinet.findCustomerById(request.customerId())
				.orElseThrow(()->new BusinessException("Cannot create Order:: No customer exist with the provided ID"));
		
		//purchase the products --> product-ms
    	var purchasedProducts=this.productClint.purchaseProducts(request.products());
		
		//persist order 
		var order = this.repository.save(mapper.toOrder(request));
		
		//persist order lines
		 for(PurchaseRequest purchaseRequest: request.products()) {
			 orderLineService.saveOrderLine(
				new OrderLineRequest(
					 null,
					 order.getId(),
					 purchaseRequest.productId(),
					 purchaseRequest.quantity()
					 )
				);
					 
		 }
		// START PAYMENT PROCESS
		 var paymentRequest =new PaymentRequest(
				 request.amount(),
				 request.paymentMethod(),
				 order.getId(),
				 order.getReference(),
				 customer);
		paymentClient.requestOrderPayment(paymentRequest);
		//send the order confirmation  --> notitfication-ms (kafka)
		 orderProducer.sendOrderConfirmation(
				 new OrderConfirmation(
						 request.reference(),
						 request.amount(),
						 request.paymentMethod(),
						 customer,
						 purchasedProducts
						 )
				 );
		return order.getId();
	}

	public List<OrderResponse> findAll() {
		return repository.findAll()
				.stream()
				.map(mapper::fromOrder)
				.collect(Collectors.toList());
	}

	public OrderResponse findById(Integer orderId) {
		return repository.findById(orderId)
				.map(mapper::fromOrder)
				.orElseThrow(()->new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
	}

	

}
