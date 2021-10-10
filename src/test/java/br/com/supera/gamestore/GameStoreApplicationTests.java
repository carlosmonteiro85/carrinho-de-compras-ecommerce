package br.com.supera.gamestore;

import java.util.Locale;

import org.springframework.boot.test.context.SpringBootTest;

import com.github.javafaker.Faker;

@SpringBootTest
public abstract class GameStoreApplicationTests {

	protected static Faker faker = Faker.instance(new Locale("pt", "BR"));

}
