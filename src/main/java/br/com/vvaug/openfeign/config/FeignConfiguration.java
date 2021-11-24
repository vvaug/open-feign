package br.com.vvaug.openfeign.config;


import feign.Logger;
import static feign.Logger.Level.FULL;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class FeignConfiguration {

    static final String DEFAULT_ERROR_MESSAGE = "An error occurred while trying to make a HTTP request to: {}";

    @Bean
    public ErrorDecoder errorDecoder(){
        return (methodKey, response) -> {
            log.error(DEFAULT_ERROR_MESSAGE, response.request().url());
            /*
                we can define custom exceptions and catch it in a RestControllerAdvice for example
                and make a smarter exception handler
             */
            throw new RuntimeException(DEFAULT_ERROR_MESSAGE.replace("{}", response.request().url()));
        };
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return FULL;
    }

}
