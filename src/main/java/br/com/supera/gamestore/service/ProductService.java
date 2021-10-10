package br.com.supera.gamestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.supera.gamestore.model.Product;
import br.com.supera.gamestore.repository.ProductRepository;


@Service
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void deleteById(long id) {
		productRepository.deleteById(id);
	}

	public List<Product> findAll(Sort sort) {
		return productRepository.findAll(sort);
	}

	public void deleteAll() {
		productRepository.deleteAll();
	}

}