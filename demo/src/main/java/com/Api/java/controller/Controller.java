package com.Api.java.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Api.java.model.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/game")
public class Controller {
    
    @Autowired
    private Services services;

    // Método GET para obtener todos los juegos
    @GetMapping
    public List<Game> get_all_games() {
        return services.get_all_games();
    }

    // Método GET para obtener un juego por ID
    @GetMapping("/{id}")
    public Game get_game_by_ID(@PathVariable("id") Long id) {
        return services.get_game_by_id(id).orElse(null);
    }
}
