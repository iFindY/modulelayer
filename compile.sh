rm -rf ./out
javac -d out/modules --module-source-path java --module-path ./libs java/de.arkadi.hello.good/de/arkadi/hello/good/* -Xlint
javac -d out/modules --module-source-path java --module-path ./libs java/de.arkadi.hello.bad/de/arkadi/hello/bad/* -Xlint
javac -d out/modules --module-source-path java --module-path ./libs java/de.arkadi.hello.caller/de/arkadi/hello/caller/* -Xlint
javac -d out/modules --module-source-path java --module-path ./libs java/de.arkadi.hello.starter/de/arkadi/hello/starter/* -Xlint

mkdir -p out/libs/good out/libs/bad out/libs/caller out/libs/starter
cp libs/gson* out/libs/good
cp libs/jackson* out/libs/bad
cp libs/log4j* out/libs/caller
jar --create --file out/libs/good/de.arkadi.hello.good.jar  -C out/modules/de.arkadi.hello.good/ . 
jar --create --file out/libs/bad/de.arkadi.hello.bad.jar  -C out/modules/de.arkadi.hello.bad/ .
jar --create --file out/libs/caller/de.arkadi.hello.caller.jar  -C out/modules/de.arkadi.hello.caller/ .
jar --create --file out/libs/starter/de.arkadi.hello.starter.jar  -C out/modules/de.arkadi.hello.starter/ .
