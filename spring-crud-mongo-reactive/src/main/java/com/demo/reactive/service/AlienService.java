package com.demo.reactive.service;

import com.demo.reactive.dao.AlienDao;
import com.demo.reactive.dto.AlienDto;
import com.demo.reactive.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlienService {

    @Autowired
    private AlienDao alienDao;

    public Flux<AlienDto> getAliens(){
        return alienDao.findAll().map(AppUtil::entityToDto);
    }

    public Mono<AlienDto> getAlien(String alienId){
        return alienDao.findById(alienId).map(AppUtil::entityToDto);
    }

    public Flux<AlienDto> getAlienInRange(int min, int max){

        return alienDao.findByAgeBetween(Range.closed(min, max));

    }

    public Mono<AlienDto> saveAlien(Mono<AlienDto> alienDtoMono){

      return  alienDtoMono.map(AppUtil::dtoToEntity)
                .flatMap(alienDao::insert)
                .map(AppUtil::entityToDto);

    }

    public Mono<AlienDto> updateAlien(Mono<AlienDto> alienDtoMono, String alienId){

        return alienDao.findById(alienId)
                 .flatMap(i -> alienDtoMono.map(AppUtil::dtoToEntity)
                 .doOnNext(e -> e.setAlienId(alienId)))
                         .flatMap(alienDao::save)
                         .map(AppUtil::entityToDto);

    }

    public Mono<Void> deleteAlien(String id){
       return alienDao.deleteById(id);
    }
}
