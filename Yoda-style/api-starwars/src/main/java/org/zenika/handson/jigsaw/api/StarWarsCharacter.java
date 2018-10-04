package org.zenika.handson.jigsaw.api;

import java.net.URL;

@SuppressWarnings("WeakerAccess")
public abstract class StarWarsCharacter {
    public final String id;
    public final String name;
    public final String description;
    public final String imageId;

    public StarWarsCharacter(String id, String name, String description, String imageId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageId = imageId;
    }

    public abstract URL getImage();


}
