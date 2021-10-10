package br.com.supera.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supera.gamestore.model.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

}
