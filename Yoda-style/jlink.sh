#!/usr/bin/env bash
BASE_DIR=`dirname $0`
${BASE_DIR}/gradlew clean jar

[ -d ${BASE_DIR}/starwarsjre ] && rm -Rf ${BASE_DIR}/starwarsjre

jlink --module-path $JAVA_HOME/jmods:${BASE_DIR}/api-starwars/build/libs/api-starwars.jar:${BASE_DIR}/api-starwars-local-impl/build/libs/api-starwars-local-impl.jar:${BASE_DIR}/starwars-http-server/build/libs/starwars-http-server.jar:${BASE_DIR}/diffutils/build/libs/diffutils.jar:${BASE_DIR}/fuzzywuzzy/build/libs/fuzzywuzzy.jar   \
--add-modules org.zenika.handson.jigsaw.api.local.impl,org.zenika.handson.jigsaw.http \
--output ${BASE_DIR}/starwarsjre --launcher death-star-launcher=org.zenika.handson.jigsaw.http/org.zenika.handson.jigsaw.http.Application \
--compress=2 --vm=server --strip-debug --no-header-files --no-man-pages
# add VM options
sed -i s/JLINK_VM_OPTIONS=/"JLINK_VM_OPTIONS=\"-Ddev=false\""/g ${BASE_DIR}/starwarsjre/bin/death-star-launcher
