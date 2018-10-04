module org.zenika.handson.jigsaw.api.local.impl {
    requires transitive org.zenika.handson.jigsaw.api;
    requires java.logging;
    requires me.xdrop.fuzzywuzzy;
    opens org.zenika.handson.jigsaw.api.local.img;

    provides org.zenika.handson.jigsaw.api.CharactersApi
            with org.zenika.handson.jigsaw.api.local.impl.InMemoryCharactersApi;
}