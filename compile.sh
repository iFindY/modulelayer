# clear
rm -rf ./out

# compile
javac -d out/modules \
  --module-path ./libs \
  --module-source-path java $(find java -name "*.java") -Xlint

# create dir
mkdir -p out/libs/{good,bad,caller,starter,database}

# copy libs
find out/libs/good out/libs/bad -type d -exec cp libs/gson-2.8.5.jar {} \;

# create jars
modules=(good bad caller starter database)
for i in "${modules[@]}"; do
  jar \
    --create \
    --file out/libs/"$i"/de.arkadi.hello."$i".jar \
    -C out/modules/de.arkadi.hello."$i"/ .
done


cp out/libs/database/* libs/
