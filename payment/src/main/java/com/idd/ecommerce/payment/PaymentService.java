package com.idd.ecommerce.payment;

import org.springframework.stereotype.Service;

import com.idd.ecommerce.notification.NotificationProducer;
import com.idd.ecommerce.notification.PaymentNotificationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	
	private final PaymentRepository repository;
	private final PaymentMapper mapper;
	private final NotificationProducer notificationProducer;
	
	public Integer createPayment(PaymentRequest request) {
		var payment=repository.save(mapper.toPayment(request));
		
		notificationProducer.sendNotification(
				new PaymentNotificationRequest(
				request.OrderReference(),
				request.amount(),
				request.paymentMethod(),
				request.customer().firstname(),
				request.customer().lastname(),
				request.customer().email()
				)
	     );
		
		return payment.getId();
	}

}
