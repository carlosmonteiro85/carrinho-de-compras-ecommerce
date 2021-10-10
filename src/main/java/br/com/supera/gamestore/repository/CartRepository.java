package br.com.supera.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supera.gamestore.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
