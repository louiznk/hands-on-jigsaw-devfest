package org.zenika.handson.jigsaw.api.local.impl;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.zenika.handson.jigsaw.api.CharactersApi;
import org.zenika.handson.jigsaw.api.StarWarsCharacter;
import org.zenika.handson.jigsaw.api.local.img.PlaceHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class InMemoryCharactersApi implements CharactersApi {

    private static final Function<String, StarWarsCharacter> lineToCharacter = (line) -> {
        final String[] columns = line.split("~");

        return new InStarWarsCharacter(columns[0], columns[1], columns[2], columns[3]);
    };
    private static final Map<String, StarWarsCharacter> charactersAsMap = loadDataFromFile();
    private static final List<StarWarsCharacter> charactersAsList =
            Collections.unmodifiableList(
                    charactersAsMap.values().stream().sorted(Comparator.comparing(c -> c.name)).collect(Collectors.toList())
            );
    private static final List<String> charactersNameAsList =
            Collections.unmodifiableList(
                    charactersAsList.stream().map(c -> c.name).collect(Collectors.toList())
            );


    // Data provided by Disney. © Disney
    // StarWars Portal https://www.starwars.com/databank
    // curl & jq & bash have super power too
    private static Map<String, StarWarsCharacter> loadDataFromFile() {
        try (var is = InMemoryCharactersApi.class.getModule().getResourceAsStream("data/sw.txt");
             var isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             var br = new BufferedReader(isr)) {
            return br.lines().map(lineToCharacter).collect(Collectors.toMap(c -> c.id, c -> c));
        } catch (IOException e) {
            e.printStackTrace();
            throw new JarJarBinksException("Missa thinkin da da file has been deleta by a gungan", e); // should not arrive and must crash
        }

    }

    /**
     * Find the Character by is id
     * <p>
     * Data provided by Marvel. © 2014 Marvel
     * https://developer.marvel.com
     *
     * @param id of the character
     * @return the character if the id exist
     */
    @Override
    public Optional<StarWarsCharacter> find(String id) {
        return Optional.ofNullable(charactersAsMap.get(id));
    }

    /**
     * Find all the Characters
     * Data provided by Marvel. © 2014 Marvel
     * https://developer.marvel.com
     *
     * @return All the characters
     */
    @Override
    public List<StarWarsCharacter> findAll() {
        return charactersAsList;
    }

    /**
     * Find all the Characters containing name ignoring case
     *
     * @param name the name (or part of the name) for searching
     * @return All the characters that contains the name
     */
    @Override
    public List<StarWarsCharacter> findByNameContaining(String name) {
        if (null == name || name.trim().isEmpty()) {
            return findAll();
        }
        final String lowerName = name.trim().toLowerCase();
        return charactersAsList.stream().filter(c -> c.name.toLowerCase().contains(lowerName)).collect(Collectors.toList());
    }


    @Override
    public List<StarWarsCharacter> fuzzySearchByName(String name, int minScore) {
        if ((null == name || name.trim().isEmpty()) || minScore < 0) {
            return findAll();
        }

        final List<ExtractedResult> results = FuzzySearch.extractSorted(name, charactersNameAsList, minScore);
        return results.stream().map(e -> charactersAsList.get(e.getIndex())).collect(Collectors.toList());


    }


    @SuppressWarnings("WeakerAccess")
    private static class InStarWarsCharacter extends StarWarsCharacter {

        public InStarWarsCharacter(String id, String name, String description, String imageId) {
            super(id, name, description, imageId);
        }

        @Override
        public URL getImage() {
            return PlaceHolder.class.getModule().getClassLoader().getResource("org/zenika/handson/jigsaw/api/local/img/".concat(imageId));
        }


    }



}

class JarJarBinksException extends RuntimeException {

    public JarJarBinksException(String message, Throwable exception) {
        super(message, exception);
    }
}
