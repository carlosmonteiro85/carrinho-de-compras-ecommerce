package br.com.supera.gamestore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.supera.gamestore.dto.response.MessageResponseDTO;
import br.com.supera.gamestore.exception.ProductNotFoundException;
import br.com.supera.gamestore.model.Product;
import br.com.supera.gamestore.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(@Autowired ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	// Salvar
	public MessageResponseDTO save(Product product) {
		productRepository.save(product);
		return MessageResponseDTO.createMessageResponse(product.getId(), "Produto cadastrado com sucesso!");
	}

	// Obter por id
	public Product loadById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	// Listando todos os produtos
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	// Delete
	public MessageResponseDTO delete(Long id) {
		productRepository.deleteById(id);
		return MessageResponseDTO.createMessageResponse(id, "Produto deletado com sucesso!");
	}

	// Atualizar produto
	public MessageResponseDTO updateById(Product product) throws ProductNotFoundException {
		verifyIfExists(product.getId());

		if (verifyIfExists(product.getId()) != null) {
			productRepository.save(product);
			return MessageResponseDTO.createMessageResponse(product.getId(), " Produto Atualizado com sucesso! ");
		}

		return MessageResponseDTO.createMessageResponse(product.getId(), " Produto informado não existe! ");
	}

	// validação se o produto ja existe
	private Product verifyIfExists(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	// Ordenação de produtos pelo maior valor
	public List<Product> findAllByOrderByPriceDesc() {
		return productRepository.findAllByOrderByPriceDesc();
	}

	// Ordenação de produtos pelo menor valor
	public List<Product> findAllByOrderByPriceAsc() {
		return productRepository.findAllByOrderByPriceAsc();
	}
	
	// Ordenação de produtos menos populares
	public List<Product> findAllByOrderByScoreDesc() {
		return productRepository.findAllByOrderByScoreDesc();
	}
	
	// Ordenação de produtos mais populares
		public List<Product> findAllByOrderByScoreAsc() {
			return productRepository.findAllByOrderByScoreAsc();
		}

}
