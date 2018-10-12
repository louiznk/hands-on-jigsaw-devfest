Vous devez configurer le système de compilation et d'exécution pour utiliser les modules.

Pour cela vous allez utiliser gradle, les fichiers de build sont déjà présent.
Il va falloir surcharger les sections de compilations et d'executions pour qu'ils utilisent Jigsaw.


les sections à surcharger sont :

 * `compileJava`
 * `compileTestJava`
 * `run` et `mainClassName`
 
Pour les tests unitaires (car vous êtes un jedi) il faut aussi modifier la section `test`

Pour plus de détail regardez le [Episode IV - Gradle, un Nouvel Espoir](https://mathieumure.github.io/devfest-nantes-jigsaw-docs/EPISODE_4.html) et les [TIPS](https://mathieumure.github.io/devfest-nantes-jigsaw-docs/TIPS.html)