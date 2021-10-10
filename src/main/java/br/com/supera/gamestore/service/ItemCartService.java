package br.com.supera.gamestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.supera.gamestore.model.ItemCart;
import br.com.supera.gamestore.repository.ItemCartRepository;

@Service
public class ItemCartService {

	private ItemCartRepository repository;

	public ItemCartService(@Autowired ItemCartRepository repository) {
		this.repository = repository;
	}

	// Salvar
	public ItemCart save(ItemCart itemCart) {
		return repository.save(itemCart);
	}

	// Obter produto por id
	public ResponseEntity<ItemCart> loadById(Long id) {
		return repository.findById(id).map(itemCart -> ResponseEntity.ok().body(itemCart))
				.orElse(ResponseEntity.notFound().build());
	}

	// Listando todos os produtos
	public List<ItemCart> findAll() {
		return repository.findAll();
	}

	// Delete
	public ResponseEntity<?> delete(Long id) {
		return repository.findById(id).map(registro -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());

	}

}
