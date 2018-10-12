```
##    ##  #######  ########     ###    
 ##  ##  ##     ## ##     ##   ## ##   
  ####   ##     ## ##     ##  ##   ##  
   ##    ##     ## ##     ## ##     ## 
   ##    ##     ## ##     ## ######### 
   ##    ##     ## ##     ## ##     ## 
   ##     #######  ########  ##     ## 
```

[Episode V - Yoda contre-attaque](https://mathieumure.github.io/devfest-nantes-jigsaw-docs/EPISODE_5.html)

Rappelez vous des conseils de maitre Yoda :

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