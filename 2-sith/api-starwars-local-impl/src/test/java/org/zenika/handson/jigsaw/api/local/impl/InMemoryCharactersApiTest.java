package org.zenika.handson.jigsaw.api.local.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.zenika.handson.jigsaw.api.CharactersApi;
import org.zenika.handson.jigsaw.api.StarWarsCharacter;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.condition.JRE.JAVA_10;
import static org.junit.jupiter.api.condition.JRE.JAVA_11;
import static org.junit.jupiter.api.condition.JRE.JAVA_9;


public class InMemoryCharactersApiTest {
    private CharactersApi api;

    @BeforeEach
    public void setUp() {
        api = new InMemoryCharactersApi();
    }

    @Test
    @DisplayName("Should find Luke Skywalker by id (4fbbf8b822e24ef08582f452) and he should have a thumbnail")
    public void shouldFindLukeSkywalkerById() {
        Optional<StarWarsCharacter> LukeSkywalker = api.find("4fbbf8b822e24ef08582f452");
        assertAll("Find Luke Skywalker by id",
                () -> assertNotNull(LukeSkywalker, "\"I should find a character with the id 4fbbf8b822e24ef08582f452\""),
                () -> assertTrue(LukeSkywalker.isPresent(), "I should find a character with the id 4fbbf8b822e24ef08582f452"),
                () -> assertEquals(LukeSkywalker.get().name, "Luke Skywalker", "It should be Luke Skywalker"),
                () -> assertTrue(new File(LukeSkywalker.get().getImage().getFile()).exists()
                        , "It should have a thumbnail"),
                () -> assertTrue(LukeSkywalker.get().getImage()
                        .getFile().endsWith("/img/luke-skywalker-main_5a38c454_461eebf5.jpeg"), "This thumbnail should be luke-skywalker-main_5a38c454_461eebf5.jpeg")
        );

    }

    @Test
    @DisplayName("Should find Luke Skywalker (again) searching the name containing \"luke s\"")
    public void shouldFindLukeSkywalkerByNameContaining() {
        List<StarWarsCharacter> characters = api.findByNameContaining("luke");
        assertAll("Find Luke Skywalker by name containing",
                () -> assertNotNull(characters, "I should find characters with the name containing \"luke s\""),
                () -> assertEquals(characters.size(), 1, "I should find 1 characters with the name containing \"luke\""),
                () -> assertEquals(characters.get(0).id, "4fbbf8b822e24ef08582f452", "It should have the id 4fbbf8b822e24ef08582f452")
        );
    }

    @Test
    @DisplayName("Should have 621 characters")
    public void shouldFindAllCharacters() {
        List<StarWarsCharacter> characters = api.findAll();
        assertEquals(characters.size(), 621, "I should find 621 characters");

    }

    @Test
    @DisplayName("Shoud fuzzy search \"walksky\" with scoring >= 50 and find 7 characters, but walksky is the first")
    public void shouldFuzzySearchByName() {
        List<StarWarsCharacter> characters = api.fuzzySearchByName("walksky", 50);
        assertAll("Find Luke Skywalker with fuzzy search",
                () -> assertNotNull(characters, "I should find some characher with name is about \"walksky\""),
                () -> assertEquals(7, characters.size(), "I should X find some characher with name is about \"walksky\""),
                () -> assertEquals("4fbfbe5ad64aa6c822310fb7", characters.get(0).id, "The first characters should be Wald (id 4fbfbe5ad64aa6c822310fb7)"),
                () -> assertEquals("4fbfbdb71d3556c822310fb7", characters.get(6).id, "The 7 characters should be Shmi Skywalker Lars (id 4fbfbdb71d3556c822310fb7)")
        );
    }

    @Test

    @EnabledIfSystemProperty(named = "JIGSAW", matches = "TRUE")
//    @EnabledIfEnvironmentVariable(named = "ENV", matches = "JIGSAW")
    // Enable only with real module cmdline (not Idea which using classpath on test)
    @DisplayName("Dynamic invoke on object that is not open to this module should throw an IllegalAccessException")
    public void shouldFailToFindClassWithJigsaw() {
        assertThrows(IllegalAccessException.class, () -> {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            lookup.findClass("java.sql.Timestamp");
        });

    }

    @Test
    @DisabledIfSystemProperty(named = "JIGSAW", matches = "TRUE")
    @DisplayName("With classpath you could dynamic invoke what you want")
    public void shouldFindClassWithoutJigsaw() throws IllegalAccessException, ClassNotFoundException {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        assertNotNull(lookup.findClass("java.sql.Timestamp"));

    }


}