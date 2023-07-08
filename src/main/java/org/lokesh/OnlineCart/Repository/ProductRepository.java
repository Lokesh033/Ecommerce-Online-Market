package org.lokesh.OnlineCart.Repository;

import org.lokesh.OnlineCart.Dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , Integer>{

	Product findByName(String name);
	
}
