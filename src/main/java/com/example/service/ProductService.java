package com.example.service;

import java.util.List;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.Product;
import com.example.entity.UserInfo;
import com.example.repository.UserInfoRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {

	@Autowired
	private UserInfoRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	List<Product> productList = loadProductsFromDB();
	
	 @PostConstruct
	    public List<Product> loadProductsFromDB() {
		 List<Product> newList = new ArrayList<>();
		 newList.add(new Product(1,"Dell",2,10000));
		 newList.add(new Product(2,"HP",3,10000));
		 newList.add(new Product(3,"ACER",1,10000));
		 newList.add(new Product(4,"APPLE",4,10000));
			return newList;
	       
	    }
	 
	

	public List<Product> getProducts(){
		return productList;
	}
	public Product getProduct(int id) {
		return productList.stream()
				.filter(product -> product.getProductId()==id)
				.findAny()
				.orElseThrow(()->new RuntimeException("Product "+ id +"Not Found"));
	}
	
	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "User Added to System";
	}
}
//productList = IntStream.rangeClosed(1,50).
//mapToObj(i -> Product.builder()
        //.productId(i)
        //.name("product " + i)
        //.qty(new Random().nextInt(10))
        //.price(new Random().nextInt(5000)).build()
        //.collect(Collectors.toList()); -->
