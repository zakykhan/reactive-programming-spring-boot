package com.demo.springbootreactivewebflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
        Mono<Object> mono = Mono.just("Zaky Khan")
                .then(Mono.error(new RuntimeException("Exception occurred")))
                .log();
        mono.subscribe(System.out::println,(e) -> System.out.println(e.getMessage()));

    }

    @Test
    public void testFlux(){
        Flux<String> fluxString = Flux.just("Spring", "Spring boot", "Hibernate")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Runtime exception occurred in flux")))
                .log();
        fluxString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }
}
