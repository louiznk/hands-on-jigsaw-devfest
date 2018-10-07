```
##    ##  #######  ########     ###    
 ##  ##  ##     ## ##     ##   ## ##   
  ####   ##     ## ##     ##  ##   ##  
   ##    ##     ## ##     ## ##     ## 
   ##    ##     ## ##     ## ######### 
   ##    ##     ## ##     ## ##     ## 
   ##     #######  ########  ##     ## 
```

[Voir aussi](fixme:url)

# Jlink

La maitrise de Jlink est nécessaire à tous maîtres Jedi.

Windu vous a laissé ces indications.

Avec BASE_DIR = le répertoire de travail de votre projet.

## Module Path (--module-path )
* Les modules du JDK ${JAVA_HOME}/jmods
* Les jar générés par gradle sont :
  * ${BASE_DIR}/api-starwars/build/libs/api-starwars.jar
  * ${BASE_DIR}/api-starwars-local-impl/build/libs/api-starwars-local-impl.jar
  * ${BASE_DIR}/starwars-http-server/build/libs/starwars-http-server.jar
  * ${BASE_DIR}/diffutils/build/libs/diffutils.jar
  * ${BASE_DIR}/fuzzywuzzy/build/libs/fuzzywuzzy.jar
  
## La liste des modules à ajouter (--add-modules)
 * org.zenika.handson.jigsaw.api.local.impl
 * org.zenika.handson.jigsaw.http

## Le lanceur (--launcher)
org.zenika.handson.jigsaw.http/org.zenika.handson.jigsaw.http.Application


# Image Docker

Pour parvenir au rand de grand maitre et approcher à la sagesse de Yoda il faut maitriser le pouvoir de contenarisation.
Vous devez créer une image minimaliste en utilisant Jlink dans une première phase de compilation

Pour cela appuyer vous sur les multi-build de docker avec comme images :

 * `openjdk:11-jdk-oracle` pour le build
 * `debian:sid-slim` pour l'image final
 
TIPS : lancer le build des jar depuis l'image 0 ainsi ./gradlew --no-daemon clean jar
