package br.com.supera.gamestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supera.gamestore.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	//pesquisa de produtos por preço de ordem decrescente 
	List<Product> findAllByOrderByPriceDesc();
	
	//pesquisa de produtos por preço de ordem crescente
	List<Product> findAllByOrderByPriceAsc();

	//pesquisa de produtos por popularidade em ordem decrescente
	List<Product> findAllByOrderByScoreDesc();
	
	//pesquisa de produtos por popularidade em ordem crescente
	List<Product> findAllByOrderByScoreAsc();
}
