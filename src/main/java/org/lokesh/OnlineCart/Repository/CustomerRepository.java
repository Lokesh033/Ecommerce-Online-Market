package org.lokesh.OnlineCart.Repository;

import org.lokesh.OnlineCart.Dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String>  {

	Customer findByEmail(String email);

	Customer findByMobile(long mobile);
	
	

}
