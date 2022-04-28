package com.demo.springbootreactivewebflux.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alien {

    private int alienId;

    private String alienName;
}
