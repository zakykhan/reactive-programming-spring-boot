package com.demo.reactive.util;

import com.demo.reactive.dto.AlienDto;
import com.demo.reactive.entity.Alien;
import org.springframework.beans.BeanUtils;

public class AppUtil {

    public static AlienDto entityToDto(Alien alien){

        AlienDto alienDto = new AlienDto();
        BeanUtils.copyProperties(alien, alienDto);
        return alienDto;

    }

    public static Alien dtoToEntity(AlienDto alienDto){

        Alien alien = new Alien();
        BeanUtils.copyProperties(alienDto, alien);
        return alien;

    }
}
