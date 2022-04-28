package com.demo.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlienDto {

    private String alienId;

    private String name;

    private String species;

    private String planet;

    private int age;
}
