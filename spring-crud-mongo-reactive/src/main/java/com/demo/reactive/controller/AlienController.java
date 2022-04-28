package com.demo.reactive.controller;

import com.demo.reactive.dto.AlienDto;
import com.demo.reactive.service.AlienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/aliens")
public class AlienController {

    @Autowired
    private AlienService service;

    @GetMapping
    public Flux<AlienDto> getAliens(){
        return service.getAliens();
    }

    @GetMapping("/{id}")
    public Mono<AlienDto> getAlien(@PathVariable("id") String id){
        return service.getAlien(id);
    }

    @GetMapping("/age-range")
    public Flux<AlienDto> getAliensBetweenAge(@RequestParam("min") int min, @RequestParam("max") int max){
        return service.getAlienInRange(min, max);
    }

    @PostMapping("/save")
    public Mono<AlienDto> saveAlien(@RequestBody Mono<AlienDto> alienDtoMono){
        return service.saveAlien(alienDtoMono);
    }

    @PutMapping("/update/{alienId}")
    public Mono<AlienDto> updateAlien(@RequestBody  Mono<AlienDto> alienDtoMono, @PathVariable String alienId){

        return service.updateAlien(alienDtoMono, alienId);
    }

    @DeleteMapping("/delete/{alienId}")
    public Mono<Void> deleteAlien(@PathVariable  String alienId){
       return service.deleteAlien(alienId);
    }

}
