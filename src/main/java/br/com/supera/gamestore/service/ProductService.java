package br.com.supera.gamestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.supera.gamestore.model.Product;
import br.com.supera.gamestore.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository repository;

	public ProductService(@Autowired ProductRepository productRepository) {
		this.repository = productRepository;
	}

	// Salvar
	public Product save(Product product) {
		return repository.save(product);
	}

	// Obter produto por id
	public ResponseEntity<Product> loadById(Long id) {
		return repository.findById(id).map(product -> ResponseEntity.ok().body(product))
				.orElse(ResponseEntity.notFound().build());
	}

	// Listando todos os produtos
	public List<Product> findAll() {
		return repository.findAll();
	}

	// Delete
	public ResponseEntity<?> delete(Long id) {
		return repository.findById(id).map(registro -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());

	}

	// Atualizar produto
	public ResponseEntity<Product> updateById(Long id, Product product) {
		return repository.findById(id).map(registro -> {
			registro.setName(product.getName());
			registro.setPrice(product.getPrice());
			registro.setScore(product.getScore());
			registro.setImage(product.getImage());

			Product updateProduct = repository.save(registro);
			return ResponseEntity.ok().body(updateProduct);
		}).orElse(ResponseEntity.notFound().build());
	}

	// Ordenação de produtos pelo maior valor
	public List<Product> findAllByOrderByPriceDesc() {
		return repository.findAllByOrderByPriceDesc();
	}

	// Ordenação de produtos pelo menor valor
	public List<Product> findAllByOrderByPriceAsc() {
		return repository.findAllByOrderByPriceAsc();
	}

	// Ordenação de produtos menos populares
	public List<Product> findAllByOrderByScoreDesc() {
		return repository.findAllByOrderByScoreDesc();
	}

	// Ordenação de produtos mais populares
	public List<Product> findAllByOrderByScoreAsc() {
		return repository.findAllByOrderByScoreAsc();
	}

}
