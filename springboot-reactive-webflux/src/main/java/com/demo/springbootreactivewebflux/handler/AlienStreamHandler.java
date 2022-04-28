package com.demo.springbootreactivewebflux.handler;

import com.demo.springbootreactivewebflux.DAO.AlienDao;
import com.demo.springbootreactivewebflux.DTO.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlienStreamHandler {

    @Autowired
    private AlienDao alienDao;

    public Mono<ServerResponse> loadAliens(ServerRequest request){

        Flux<Alien> stream = alienDao.getAliensStream();

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(stream, Alien.class);

    }
}
