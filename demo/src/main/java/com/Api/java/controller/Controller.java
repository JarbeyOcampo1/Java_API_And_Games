package com.Api.java.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Api.java.model.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/game")

public class Controller {
    
    @Autowired
    private Services services;

    // 
    @GetMapping
    public List <Game> get_all_gamess () {
        return services.get_all_games();
    }

    
    @GetMapping("/{id}")
    public Game get_game_by_ID(@PathVariable("id") Long id) {
        return services.get_game_by_id(id).orElse(null);
    }


    @PostMapping
    public Game add_game(@RequestBody Game game) {
        return services.add_game(game);
    }
    

    @PutMapping("/{id}")
    public Game update_games(@PathVariable("id") Long id, @RequestBody Game game) {
        return services.update_game(id, game);
    }
    
    
}
