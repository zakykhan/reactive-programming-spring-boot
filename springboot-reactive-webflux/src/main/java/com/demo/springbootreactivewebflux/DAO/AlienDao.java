package com.demo.springbootreactivewebflux.DAO;

import com.demo.springbootreactivewebflux.DTO.Alien;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class AlienDao {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Alien> getAliens(){
       return IntStream.rangeClosed(1, 10)
               .peek(AlienDao::sleepExecution)
               .peek(i -> System.out.println("Aliens Count:- "+ i))
               .mapToObj(i -> new Alien(i, "KillMonger " +i))
               .collect(Collectors.toList());
    }

    public Flux<Alien> getAliensStream(){
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Aliens Count:- "+ i))
                .map(i -> new Alien(i, "KillMonger " +i));

    }

    public Flux<Alien> getAliensList(){
        return Flux.range(1, 50)
                .doOnNext(i -> System.out.println("Aliens Count:- "+ i))
                .map(i -> new Alien(i, "KillMonger " +i));

    }
}
