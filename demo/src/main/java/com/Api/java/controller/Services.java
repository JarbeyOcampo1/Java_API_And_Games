package com.Api.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.Api.java.model.Game;

@Service
public class Services {

    private static final String FILE_PATH = "games.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    // Método para obtener todos los juegos
    public List<Game> get_all_games() {
        try {
            File file = new ClassPathResource(FILE_PATH).getFile();
    
            if (!file.exists()) {
                file.createNewFile();
                objectMapper.writeValue(file, new ArrayList<Game>());
            }

            Game[] gamesArray = objectMapper.readValue(file, Game[].class);
            return new ArrayList<>(Arrays.asList(gamesArray));
            
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Método para obtener un juego por ID
    public Optional<Game> get_game_by_id(Long id) {
        List<Game> games = get_all_games();
        return games.stream().filter(game -> game.getId().equals(id)).findFirst();
    }

}
