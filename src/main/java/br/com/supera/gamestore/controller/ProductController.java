package br.com.supera.gamestore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.gamestore.model.Product;
import br.com.supera.gamestore.service.ProductService;



@RestController
@RequestMapping("products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public Product create(@RequestBody Product product) {
		return productService.save(product);
	}

	@PutMapping("{id}")
	public ResponseEntity<Product> update(@PathVariable("id") long id, @RequestBody Product product) {
		return productService.findById(id).map(record -> {
			record.setName(product.getName());
			record.setImage(product.getImage());
			record.setPrice(product.getPrice());
			record.setScore(product.getScore());
			Product updated = productService.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return productService.findById(id).map(record -> {
			productService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		return productService.findById(id).map(p -> ResponseEntity.ok().body(p))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public List<Product> findAll(@RequestParam(defaultValue = "id,asc") String[] sort) {

		List<Order> orders = new ArrayList<>();

		if (sort[0].contains(",")) {
			// irá ordenar mais que dois campos
			// sortOrder="field, direction"
			for (String sortOrder : sort) {
				String[] _sort = sortOrder.split(",");
				orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
			}
		} else {
			// sort=[field, direction]
			orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		}
		
		return productService.findAll(Sort.by(orders));
	}
	
	private Sort.Direction getSortDirection(String direction) {
		if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}
		return Sort.Direction.ASC;
	}

}
