package br.com.supera.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supera.gamestore.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
