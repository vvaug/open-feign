package br.com.vvaug.openfeign;

import br.com.vvaug.openfeign.client.ViacepClient;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class OpenFeignApplication {

	@Autowired
	private ViacepClient client;

	public static void main(String[] args) {
		SpringApplication.run(OpenFeignApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(){
		return (args -> {
			var response = client.verifyZipCode("09852140");
			System.out.println(response);
		});
	}
}
