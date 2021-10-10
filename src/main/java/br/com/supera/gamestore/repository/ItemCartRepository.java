package br.com.supera.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supera.gamestore.model.ItemCart;

public interface ItemCartRepository extends JpaRepository<ItemCart, Long>{
}
