package br.com.supera.gamestore.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.supera.gamestore.model.Checkout;
import br.com.supera.gamestore.repository.CheckoutRepository;


@Service
public class CheckoutService {

	private CheckoutRepository checkoutRepository;

	public CheckoutService(CheckoutRepository checkoutRepository) {
		this.checkoutRepository = checkoutRepository;
	}

	public Checkout save(Checkout checkout) {
		return checkoutRepository.save(checkout);
	}

	public Optional<Checkout> findById(long id) {
		return checkoutRepository.findById(id);
	}

	public void deleteById(long id) {
		checkoutRepository.deleteById(id);
	}

}