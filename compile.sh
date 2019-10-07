# clear
rm -rf ./out

# compile
javac -d out/modules \
  --module-path ./libs \
  --module-source-path java $(find java -name "*.java") -Xlint

# create dir
mkdir -p out/libs/{left,right,ui,starter,database,provider,serviceinterface}

# copy libs
find out/libs/left out/libs/right -type d -exec cp libs/gson-2.8.5.jar {} \;

# create jars
modules=(left right ui starter database provider serviceinterface)
for i in "${modules[@]}"; do
  jar \
    --create \
    --file out/libs/"$i"/de.arkadi.hello."$i".jar \
    -C out/modules/de.arkadi.hello."$i"/ .
done

jar  --create --file out/libs/starter/de.arkadi.hello.starter.jar --main-class de.arkadi.hello.starter.Main -C out/modules/de.arkadi.hello.starter/ .

# test deps on db module
cp out/libs/database/* libs/
