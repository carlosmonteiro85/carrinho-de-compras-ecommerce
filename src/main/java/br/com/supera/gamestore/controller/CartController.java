package br.com.supera.gamestore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.gamestore.model.Cart;
import br.com.supera.gamestore.model.ItemCart;
import br.com.supera.gamestore.service.CartService;

@RestController
@RequestMapping("cart")
public class CartController {
	
	private CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@PostMapping
	public Cart create(@RequestBody Cart cart) {
		
		// Define no "itens do carrinho" no "carrinho" 
		cart.getCartItens().stream().forEach(item -> item.setCart(cart));
		
		return cartService.save(cart);
	}
	
	@PostMapping("addItemCart/{id}")
	public ResponseEntity<Cart> addItemCart(@PathVariable("id") long id, @RequestBody ItemCart cartItem) {
		return cartService.findById(id).map(record -> {
			cartItem.setCart(record);
			record.getCartItens().add(cartItem);
			Cart updated = cartService.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("removeItemCart/{id}")
	public ResponseEntity<Cart> removeItemCart(@PathVariable("id") long id, @RequestBody ItemCart cartItem) {
		return cartService.findById(id).map(record -> {
			boolean remove = record.getCartItens().remove(cartItem);
			if (!remove) {
				return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
			}
			Cart updated = cartService.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cart> update(@PathVariable("id") long id, @RequestBody Cart cart) {
		return cartService.findById(id).map(record -> {
			
			List<ItemCart> itensToPersist = new ArrayList<>();
			
			// Atualiza os items já persistidos
			record.getCartItens().stream()
				.filter(itemPersisted -> cart.getCartItens().contains(itemPersisted))
				.forEach(itemPersisted -> {
					int indexOf = cart.getCartItens().indexOf(itemPersisted);
					itemPersisted.setQuantity(cart.getCartItens().get(indexOf).getQuantity());
					itensToPersist.add(itemPersisted);
				});
			
			// Insere itens novos
			cart.getCartItens().stream()
				.filter(itemFromJson -> !record.getCartItens().contains(itemFromJson))
				.forEach(itemNew -> {
					 //Define no "itens do carrinho" o "carrinho"
					 itemNew.setCart(cart);
					 itensToPersist.add(itemNew);
				});
			
			record.getCartItens().clear();
			record.getCartItens().addAll(itensToPersist);
			Cart updated = cartService.save(record);
			
			return ResponseEntity.ok().body(updated);
			
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return cartService.findById(id).map(c -> {
			cartService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Cart> findById(@PathVariable long id) {
		return cartService.findById(id)
				.map(c -> ResponseEntity.ok().body(c))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public List<Cart> findAll() {
		return cartService.findAll();
	}

}
