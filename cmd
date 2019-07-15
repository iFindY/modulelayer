javac -d out --module-source-path java java/de.arkadi.hello.caller/de/arkadi/hallo/caller/* -Xlint
javac -d out --module-source-path java java/de.arkadi.hello.printer/de/arkadi/hello/printer/*

jar --create --file out/app/de.arkadi.hello.caller.jar  -C out/de.arkadi.hello.caller/ .
jar --create --file out/v2/de.arkadi.hello.printer-2.jar --module-version 2 -C out/de.arkadi.hello.printer/ .

java -p app -m de.arkadi.hello.caller/de.arkadi.hallo.caller.Caller ./v1 ./v2
