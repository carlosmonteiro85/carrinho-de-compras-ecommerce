package br.com.supera.gamestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.gamestore.dto.response.MessageResponseDTO;
import br.com.supera.gamestore.exception.ProductNotFoundException;
import br.com.supera.gamestore.model.Product;
import br.com.supera.gamestore.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	public List<Product> listAll() {
		return service.findAll();
	}

	@PostMapping
	public MessageResponseDTO createFornecedor(@RequestBody Product product) {
		return service.save(product);
	}

	@GetMapping("/{id}")
	public Product findById(@PathVariable Long id) throws ProductNotFoundException {
		return service.loadById(id);
	}

	@PutMapping("/{id}")
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody Product product)
			throws ProductNotFoundException {
		return service.updateById(product);
	}

	@DeleteMapping("/{id}")
	public MessageResponseDTO deleteById(@PathVariable Long id) throws ProductNotFoundException {
		return service.delete(id);
	}

}
