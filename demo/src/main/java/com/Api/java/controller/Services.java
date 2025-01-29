package com.Api.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.Api.java.model.Game;

@Service
public class Services {

    private static final String FILE_PATH ="src/main/resources/games.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    // Method to add a new game
    public List<Game> get_all_games() {
        try {
            File file = new File(FILE_PATH);
            // Si el archivo no existe, lo crea y lo inicializa con un array vacío
            if (!file.exists()) {
                file.createNewFile();
                objectMapper.writeValue(file, new ArrayList<Game>());
            }
            // Leer el archivo y convertirlo a una lista mutable
            Game[] gamesArray = objectMapper.readValue(file, Game[].class);
            return new ArrayList<>(Arrays.asList(gamesArray));
    
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Method to  a new game
    public Optional <Game> get_game_by_id(Long id) {
        List<Game> games = get_all_games();
        return games.stream().filter(game -> game.getId() == id).findFirst();
    }
    
    public Game add_game(Game game) {
        List<Game> games = get_all_games();
        
        // Asegurar que se genere un ID válido
        game.setId(games.isEmpty() ? 1 : games.get(games.size() - 1).getId() + 1);
        
        games.add(game);
        saveGames(games);
        return game;
    }


    public Game update_game(Long id, Game game) {
        List<Game> games = get_all_games();
        games.removeIf(g -> g.getId().equals(id));
        game.setId(id); // Asegúrate de que el ID del juego actualizado sea el mismo
        games.add(game);
        saveGames(games);
        return game;
    }


    private void saveGames(List<Game> games) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), games);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
