package com.demo.reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "alien")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Alien {

    @Id
    private String alienId;

    private String name;

    private String species;

    private String planet;

    private int age;
}
