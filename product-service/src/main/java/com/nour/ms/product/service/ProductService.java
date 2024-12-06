package com.nour.ms.product.service;

import com.nour.ms.product.dto.ProductRequest;
import com.nour.ms.product.dto.ProductResponse;
import com.nour.ms.product.model.Product;
import com.nour.ms.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class ProductService {
	private final ProductRepository productRepository;

	public ProductResponse createProduct(ProductRequest productRequest){
		Product product = Product.builder()
				.skuCode(productRequest.skuCode())
				.name(productRequest.name())
				.description(productRequest.description())
				.price(productRequest.price())
				.build();
		productRepository.save(product);
		log.info("Product created successfully");
		return new ProductResponse(product.getId(), product.getSkuCode(), product.getName(), product.getDescription(), product.getPrice());
	}

	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll()
				.stream()
				.map(product -> new ProductResponse(product.getId(), product.getSkuCode(), product.getName(), product.getDescription(), product.getPrice()))
				.toList();
	}
}