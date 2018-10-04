package org.zenika.handson.jigsaw.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface CharactersApi {

    /**
     * Serialize StarWarsCharacter to Json
     *
     * @param character the character to serialize
     * @return json representation
     */
    static String startWarsCharacterToJson(StarWarsCharacter character) {
        final StringBuilder sb = new StringBuilder("{\n")
                .append("  \"id\": \"").append(character.id).append("\",\n")
                .append("  \"name\": \"").append(encodeStringToJson(character.name)).append("\",\n")
                .append("  \"description\": \"").append(encodeStringToJson(character.description)).append("\"\n")
                .append("}");

        return sb.toString();
    }

    static String encodeStringToJson(String unencoded) {
        return unencoded
                .replace("\"", "\\\"");

    }

    /**
     * Serialize a List of StarWarsCharacter to Json Array of StarWarsCharacter
     *
     * @param characters list of characters to serialize
     * @return the Json array of StarWarsCharacter
     */
    static String startWarsCharactersToJson(List<StarWarsCharacter> characters) {
        final StringBuilder sb = new StringBuilder(2048)
                .append("{ \"characters\": [\n")
                .append(characters.stream().map(CharactersApi::startWarsCharacterToJson)
                        .collect(Collectors.joining(",\n")))
                .append("] }\n");

        return sb.toString();
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
    Optional<StarWarsCharacter> find(String id);

    /**
     * Find all the Characters
     * Data provided by Marvel. © 2014 Marvel
     * https://developer.marvel.com
     *
     * @return All the characters
     */
    List<StarWarsCharacter> findAll();

    /**
     * Find all the Characters containing name ignoring case
     *
     * @param name the name (or part of the name) for searching
     * @return All the characters that contains the name
     */
    List<StarWarsCharacter> findByNameContaining(String name);

    /**
     * Fuzzy search Characters by name. The result is orderer by the score
     *
     * @param name         the name (or part of the name) for searching
     * @param minScore the minimumScore (between 0 and 100, 100 is strictly equals)
     * @return The characters that matching name with score &ge; minScore
     */
    List<StarWarsCharacter> fuzzySearchByName(String name, int minScore);



}
