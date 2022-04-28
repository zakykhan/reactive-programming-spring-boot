package com.demo.reactive;

import com.demo.reactive.controller.AlienController;
import com.demo.reactive.dto.AlienDto;
import com.demo.reactive.service.AlienService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(AlienController.class)
class SpringCrudMongoReactiveApplicationTests {

    @Autowired
    private WebTestClient testClient;

    @MockBean
    private AlienService service;

    @Test
    public void assAlienTest(){
        Mono<AlienDto> alien = Mono.just(new AlienDto("111", "Thanos", "WarMonger" ,"Chitarui", 150));
        when(service.saveAlien(alien))
                .thenReturn(alien);

        testClient.post().uri("/aliens/save")
                .body(Mono.just(alien), AlienDto.class)
                .exchange()
                .expectStatus().isOk(); //200
    }

    @Test
    public void getAliensTest(){
        Flux<AlienDto> alienDtoFlux = Flux.just(new AlienDto("111", "Thanos", "WarMonger" ,"Chitarui", 150),
                new AlienDto("112", "Drax", "Galaxy inmate" ,"Droombat", 200));
        when(service.getAliens())
                .thenReturn(alienDtoFlux);

        Flux<AlienDto> responseBody = testClient.get().uri("/aliens")
                .exchange()
                .expectStatus().isOk() //200
                .returnResult(AlienDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new AlienDto("111", "Thanos", "WarMonger" ,"Chitarui", 150))
                .expectNext(new AlienDto("112", "Drax", "Galaxy inmate" ,"Droombat", 200))
                .verifyComplete();

    }

    @Test
    public void getAlienTest()
    {
        Mono<AlienDto> alienDtoMono=Mono.just(new AlienDto("111", "Thanos", "WarMonger" ,"Chitarui", 150));
        when(service.getAlien(any())).thenReturn(alienDtoMono);

        Flux<AlienDto> responseBody = testClient.get().uri("/aliens/111")
                .exchange()
                .expectStatus().isOk()
                .returnResult(AlienDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p->p.getName().equals("Thanos"))
                .verifyComplete();
    }

    @Test
    public void updateProductTest(){
        Mono<AlienDto> alienDtoMono=Mono.just(new AlienDto("111", "Thanos", "WarMonger" ,"Chitarui", 150));
        when(service.updateAlien(alienDtoMono,"102")).thenReturn(alienDtoMono);

        testClient.put().uri("/aliens/update/102")
                .body(Mono.just(alienDtoMono),AlienDto.class)
                .exchange()
                .expectStatus().isOk();//200
    }

    @Test
    public void deleteAlienTest(){
        given(service.deleteAlien(any())).willReturn(Mono.empty());
        testClient.delete().uri("/aliens/delete/111")
                .exchange()
                .expectStatus().isOk();//200
    }
    @Test
    void contextLoads() {
    }

}
