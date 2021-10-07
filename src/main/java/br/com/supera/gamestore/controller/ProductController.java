package br.com.supera.gamestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.gamestore.model.Product;
import br.com.supera.gamestore.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	public List<Product> listAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product>  findById(@PathVariable Long id){
		return service.loadById(id);
	}
	
	@PostMapping
	public Product createFornecedor(@RequestBody Product product) {
		return service.save(product);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Product> updateById(@PathVariable Long id, @RequestBody Product product){
		return service.updateById(id, product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteById(@PathVariable Long id){
		return service.delete(id);
	}

	// Obtem a lista dos produtos ordenadas por maior preço
	@GetMapping("/orderByPriceBigger")
	public List<Product> orderByDescPrice() {
		return service.findAllByOrderByPriceDesc();
	}

	// Obtem a lista dos produtos ordenadas por menor preço
	@GetMapping("/orderByPriceLowest")
	public List<Product> orderByAscPrice() {
		return service.findAllByOrderByPriceAsc();
	}

	// Obtem a lista dos produtos ordenada por maior popularidade
	@GetMapping("/orderByScoreBigger")
	public List<Product> orderByDescScore() {
		return service.findAllByOrderByScoreDesc();
	}

	// Obtem a lista dos produtos ordenada por menor popularidade
	@GetMapping("/orderByScoreLowest")
	public List<Product> orderByAscScore() {
		return service.findAllByOrderByScoreAsc();
	}

}
