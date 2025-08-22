package com.idd.ecommerce.notification;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.idd.ecommerce.kafka.order.OrderConfirmation;
import com.idd.ecommerce.kafka.payment.PaymentConfirmation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

	@Id
	private String id;
	private NotificationType type;
	private LocalDateTime notificationDate;
	private OrderConfirmation orderConfirmation;
	private PaymentConfirmation paymentConfirmation;
}
