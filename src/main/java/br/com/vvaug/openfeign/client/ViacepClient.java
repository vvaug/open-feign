package br.com.vvaug.openfeign.client;

import br.com.vvaug.openfeign.config.FeignConfiguration;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static br.com.vvaug.openfeign.client.ViacepClient.SERVICE_NAME;

@FeignClient(name = SERVICE_NAME, url = "https://viacep.com.br/ws", configuration = FeignConfiguration.class)
public interface ViacepClient {

    static final String SERVICE_NAME = "via-cep-client";

    @GetMapping("/{zipCode}/json")
    @Bulkhead(name = SERVICE_NAME, fallbackMethod = "verifyZipCodeFallBack")
    String verifyZipCode(@PathVariable String zipCode);


    /*
        Resillience4J Circuit Breaker
        We can also define it outside the feign client interface
        like at the serviceLayer
     */

    default String verifyZipCodeFallBack(String zipCode, Throwable throwable){
        return "responseFromResilience4JCircuitBreaker";
    }

}
