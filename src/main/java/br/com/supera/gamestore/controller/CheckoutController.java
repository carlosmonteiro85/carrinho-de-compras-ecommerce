package br.com.supera.gamestore.controller;

import java.util.Optional;

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
import br.com.supera.gamestore.model.Checkout;
import br.com.supera.gamestore.service.CartService;
import br.com.supera.gamestore.service.CheckoutService;

@RestController
@RequestMapping("checkout")
public class CheckoutController {

	private CheckoutService checkoutService;
	private CartService cartService;

	public CheckoutController(CheckoutService checkoutService, CartService cartService) {
		this.checkoutService = checkoutService;
		this.cartService = cartService;
	}

	@PostMapping
	public Checkout create(@RequestBody Checkout checkout) {
		Optional<Cart> cart = cartService.findById(checkout.getCart().getId());
		checkout.setCart(cart.orElseGet(null));
		return checkoutService.save(checkout);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Checkout> update(@PathVariable("id") long id, @RequestBody Checkout checkout) {
		return checkoutService.findById(id).map(record -> {
			Checkout updated = checkoutService.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return checkoutService.findById(id).map(record -> {
			checkoutService.deleteById(record.getId());
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = { "pre-checkout/{id}" })
	public ResponseEntity<Checkout> findById(@PathVariable long id) {
		return cartService.findById(id).map(cart -> {
			Checkout checkout = new Checkout();
			checkout.setCart(cart);
			return ResponseEntity.ok().body(checkout);
		}).orElse(ResponseEntity.notFound().build());
	}

}
