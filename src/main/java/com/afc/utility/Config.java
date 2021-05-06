package com.afc.utility;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.afc.repository.CustomerRepository;

@Configuration
public class Config {

	@Autowired
	private CustomerRepository cr;

	@Bean
	public CommandLineRunner runner() {
		return args -> {
			
		};

	}

	public String name() {
		var str = new String[] { "Syed Mohiuddin", "Mohd Obaid Ahmed", "Mohd Hakeem", "Mohd Ahmed", "Mohd Abraaz",
				"Imran Pasha", "Mohd Rafeeq", "Mohd Rehan", "Mohd Fouad Rehan", "Farooq Ahmed", "Ali", "Faazi", "Bye" };
		return str[new Random().nextInt(str.length - 1)];
	}

	public int crate() {
		return new Random().nextInt(10);
	}

	public String number() {
		var str = new StringBuilder();
		for (var i = 0; i < 10; i++)
			str.append(new Random().nextInt(10));
		return str.toString();
	}

	public String billId() {
		var customerId = cr.findTopByOrderByIdDesc();
		if (customerId.isPresent()) {
			var customer = customerId.get();
			var id = Integer.parseInt(customer.getBillId().substring(4));
			return "CUST" + (id + 1);
		}
		return "CUST1";
	}
}
