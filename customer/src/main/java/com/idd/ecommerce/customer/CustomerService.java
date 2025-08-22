package com.idd.ecommerce.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.idd.ecommerce.exception.CustomerNotFoundException;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository repository;
	private final CustomerMapper mapper;
	
	public String createCustomer(@Valid CustomerRequest request) {
		var customer=repository.save(mapper.toCustomer(request));
		return customer.getId();
	}

	public void updateCustomer(@Valid CustomerRequest request) {
		var customer=repository.findById(request.id())
				.orElseThrow(()->new CustomerNotFoundException(
						String.format("Cannot update customer:: No customer found with the provided ID:: %s", request.id())
						));
		mergerCustomer(customer,request);
		repository.save(customer);
	}

	private void mergerCustomer(Customer customer, @Valid CustomerRequest request) {
	     if (StringUtils.isNotBlank(request.firstname())) {
			customer.setFirstname(request.firstname());
		}
	     if (StringUtils.isNotBlank(request.lastname())) {
			customer.setLastname(request.lastname());
		}
	     if (StringUtils.isNotBlank(request.email())) {
			customer.setEmail(request.email());
		}
	     if (request.address() !=null) {
			customer.setAddress(request.address());
		}
		
	}

	public List<CustomerResponse> findAllCustomers() {
		return repository.findAll()
				.stream()
				.map(mapper::fromCustomer)
				.collect(Collectors.toList());
	}

	public Boolean existsById(String customerId) {
		return repository.findById(customerId)
				.isPresent();
	}

	public CustomerResponse findById(String customerId) {
		return repository.findById(customerId)
				.map(mapper::fromCustomer)
				.orElseThrow(()->new CustomerNotFoundException(String.format("No customer found with the provided ID:: %s", customerId)));
	}

	public void deleteCustomer(String customerId) {
		repository.deleteById(customerId);
		
	}

}
