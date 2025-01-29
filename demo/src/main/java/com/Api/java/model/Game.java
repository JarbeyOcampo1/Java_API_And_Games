package com.Api.java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    //API attributes
    private Long id;
    private String name;
    private String gender;
    private String platform;
    private String developer;
    private String release_date;
    private String description;

}
