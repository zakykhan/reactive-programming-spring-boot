package com.demo.springbootreactivewebflux.controller;

import com.demo.springbootreactivewebflux.DTO.Alien;
import com.demo.springbootreactivewebflux.service.AlienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/aliens")
public class AlienController {

    @Autowired
    private AlienService alienService;

    @GetMapping("/getAll")
    public List<Alien> getAllAliens(){

       return alienService.getAllAliens();

    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Alien> getAllAlienList(){

        return alienService.getAllAliensStream();

    }
}
