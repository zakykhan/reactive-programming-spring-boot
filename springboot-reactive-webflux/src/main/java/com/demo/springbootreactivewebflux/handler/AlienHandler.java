package com.demo.springbootreactivewebflux.handler;

import com.demo.springbootreactivewebflux.DAO.AlienDao;
import com.demo.springbootreactivewebflux.DTO.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlienHandler {

    @Autowired
    private AlienDao alienDao;

    public Mono<ServerResponse> loadAliens(ServerRequest request){
        Flux<Alien> list = alienDao.getAliensList();

       return ServerResponse.ok().body(list, Alien.class);
    }

    public Mono<ServerResponse> findAlien(ServerRequest request){
      int alienId =  Integer.parseInt(request.pathVariable("input"));
      //alienDao.getAliensList().filter(i -> i.getAlienId() == alienId).take(1).single();
        Mono<Alien> next = alienDao.getAliensList().filter(i -> i.getAlienId() == alienId).next();

        return ServerResponse.ok().body(next, Alien.class);


    }

    public Mono<ServerResponse> saveAlien(ServerRequest request){
        Mono<Alien> alienMono = request.bodyToMono(Alien.class);
        Mono<String> map = alienMono.map(result -> result.getAlienId() + " : " + result.getAlienName());
        return ServerResponse.ok().body(map, String.class);
    }
}
