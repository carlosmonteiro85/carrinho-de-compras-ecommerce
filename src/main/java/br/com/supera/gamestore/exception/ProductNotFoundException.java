package br.com.supera.gamestore.exception;

public class ProductNotFoundException extends Exception {

private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException(Long id) {
        super("Nenhum produto com o id:  " + id);
	}
}
