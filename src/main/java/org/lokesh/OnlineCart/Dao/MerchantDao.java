package org.lokesh.OnlineCart.Dao;

import org.lokesh.OnlineCart.Dto.Merchant;
import org.lokesh.OnlineCart.Dto.Product;
import org.lokesh.OnlineCart.Repository.MerchantRepository;
import org.lokesh.OnlineCart.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {

	@Autowired
	MerchantRepository merchantRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public Merchant findByEmail(String email) {
		// TODO Auto-generated method stub
		return merchantRepository.findByEmail(email);
	}

	public Merchant findByMobile(long mobile) {
		// TODO Auto-generated method stub
		return merchantRepository.findByMobile(mobile);
	}

	public Merchant save(Merchant merchant) {
		
		return merchantRepository.save(merchant);
	}

	public Product findProductByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByName(name);
	}

	public Product findProductById(int id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElse(null);
	}

	public void removeProduct(Product product) {
		// TODO Auto-generated method stub
		productRepository.delete(product);
	}

	
	
}

