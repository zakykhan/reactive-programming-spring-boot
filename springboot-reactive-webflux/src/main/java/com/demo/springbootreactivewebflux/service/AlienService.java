package com.demo.springbootreactivewebflux.service;

import com.demo.springbootreactivewebflux.DAO.AlienDao;
import com.demo.springbootreactivewebflux.DTO.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class AlienService {

    @Autowired
    private AlienDao alienDao;

    public List<Alien> getAllAliens(){

        long startTime = System.currentTimeMillis();
        List<Alien> aliens = alienDao.getAliens();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time:-  "+ (endTime - startTime));
        return aliens;
    }

    public Flux<Alien> getAllAliensStream(){

        long startTime = System.currentTimeMillis();
        Flux<Alien> aliens = alienDao.getAliensStream();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time:-  "+ (endTime - startTime));
        return aliens;
    }
}
