Vous devez créer les fichiers qui vont décrirent les différents modules.

Il s'agit des fichiers module-info.java qui devront être à la racine des sources de chaque projets.
Par exemple pour le projet `api-starwars` le fichier `module-info.java` sera dans `api-starwars\src\main\java`

Comme expliquer dans les [TIPS](fixme:url) voici les principales options que l'on peut retrouver dans un module :

```java
module nom.unique.de.mon.module {
    exports nom.du.package.aa; // rend accessible les objets publiques nom.du.package.aa du package
    exports nom.du.package.bb; // rend accessible les objets publiques nom.du.package.bb du package

    opens nom.du.package.aa; // ouvre à la reflexivité le package nom.du.package.aa, cela permet aussi d'accéder aux ressources du package

    requires nom.dun.autre.module; // permet d'utiliser les objets publiques du module nom.dun.autre.module
    requires nom.dun.second.module; // permet d'utiliser les objets publiques du module nom.dun.second.module

    provides nom.du.package.aa.InterfaceFoo with nom.du.package.cc.FooImpl; // fournit l'implémentation nom.du.package.cc.FooImpl de l'interface nom.du.package.aa.InterfaceFoo pour le ServiceLoader

    uses nom.dun.autre.module.InterfaceBar; // Permettra au ServiceLoader de récupérer les implémentations "provided" de l'interface nom.dun.autre.module.InterfaceBar présentes au runtime dans le module-path
}
```

Créer tel que décrit dans [Episode III - La revanche des Sith Maven](fixme:url)dans l'ordre des dépendances les descripteurs des modules :

 * api-starwars
 * diffutils
 * fuzzywuzzy
 * api-starwars-local-impl
 * starwars-vertx-http-server


