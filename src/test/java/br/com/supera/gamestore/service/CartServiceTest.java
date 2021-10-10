package br.com.supera.gamestore.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.supera.gamestore.GameStoreApplicationTests;
import br.com.supera.gamestore.model.Cart;
import br.com.supera.gamestore.model.ItemCart;
import br.com.supera.gamestore.model.Product;



public class CartServiceTest extends GameStoreApplicationTests {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@Test
	public void insertCart() {

		// Cenário
		Product product1 = generateProductForTest();
		Product product2 = generateProductForTest();

		Cart cart = new Cart();
		cart.getCartItens().add(new ItemCart(product1, cart, 3));
		cart.getCartItens().add(new ItemCart(product2, cart, 5));

		// Ação
		cartService.save(cart);

		// Verificações
		assertNotNull(cart.getId());

		Cart cartPersited = cartService.findById(cart.getId()).get();

		assertNotNull(cartPersited);
		assertEquals(2, cartPersited.getCartItens().size());
	}
	
	@Test
	public void addItemCart() {

		// Cenário
		Product product1 = generateProductForTest();
		Product product2 = generateProductForTest();

		Cart cart = new Cart();
		cart.getCartItens().add(new ItemCart(product1, cart, 3));
		cart.getCartItens().add(new ItemCart(product2, cart, 5));
		cartService.save(cart);
		
		Cart cartWithNewItem = cartService.findById(cart.getId()).get();
		Product product3 = generateProductForTest();
		cartWithNewItem.getCartItens().add(new ItemCart(product3, cartWithNewItem, 10));
		cartService.save(cartWithNewItem);

		// Verificações
		Cart cartPersisted = cartService.findById(cart.getId()).get();
		assertEquals(3, cartPersisted.getCartItens().size());
	}
	
	@Test
	public void removeItemCart() {

		// Cenário
		Product product1 = generateProductForTest();
		Product product2 = generateProductForTest();

		Cart cart = new Cart();
		cart.getCartItens().add(new ItemCart(product1, cart, 3));
		cart.getCartItens().add(new ItemCart(product2, cart, 5));
		cartService.save(cart);
		
		Cart cartWithRemovedItem = cartService.findById(cart.getId()).get();
		cartWithRemovedItem.getCartItens().remove(0);
		cartService.save(cartWithRemovedItem);

		// Verificações
		Cart cartPersisted = cartService.findById(cart.getId()).get();
		assertEquals(1, cartPersisted.getCartItens().size());
	}
	
	@Test
	public void deleteCartId() {

		// Cenário
		Product product1 = generateProductForTest();
		Product product2 = generateProductForTest();

		Cart cart = new Cart();
		cart.getCartItens().add(new ItemCart(product1, cart, 3));
		cart.getCartItens().add(new ItemCart(product2, cart, 5));
		cartService.save(cart);

		// Ação
		cartService.deleteById(cart.getId());

		// Verificações
		Cart cartRemoved = cartService.findById(cart.getId()).orElse(null);
		assertNull(cartRemoved);
	}

	private Product generateProductForTest() {
		Product product = new Product();
		product.setImage(faker.avatar().image());
		product.setName(faker.commerce().productName());
		product.setPrice(new BigDecimal(faker.number().randomDouble(2, 10, 10000)));
		product.setScore(faker.random().nextInt(0, 1000));
		return productService.save(product);
	}

}


