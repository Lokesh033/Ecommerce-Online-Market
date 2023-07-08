package org.lokesh.OnlineCart.Dao;

import org.lokesh.OnlineCart.Dto.Customer;
import org.lokesh.OnlineCart.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
	
	@Autowired
	CustomerRepository customerRepository;

	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		return customerRepository.findByEmail(email);
	}

	public Customer findByMobile(long mobile) {
		// TODO Auto-generated method stub
		return customerRepository.findByMobile(mobile);
	}

	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

}
