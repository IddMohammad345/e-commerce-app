package com.idd.ecommerce.payment;

import static jakarta.persistence.EnumType.STRING;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue
	private Integer id;
	private BigDecimal amount;
	@Enumerated(STRING)
	private PaymentMethod paymentMethod;
	private Integer orderId;
	@CreatedDate
	@Column(updatable = false,nullable = false)
	private LocalDateTime createdDate;
	@LastModifiedBy
	@Column(insertable = false)
	private LocalDateTime lastModifiedDate;
}
