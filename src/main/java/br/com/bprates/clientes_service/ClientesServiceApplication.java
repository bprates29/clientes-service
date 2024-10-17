package br.com.bprates.clientes_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClientesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesServiceApplication.class, args);
	}

}
