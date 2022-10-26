# Preparation
```shell
sdk intall java 22.3.r17-grl
echo 'java=22.3.r17-grl' > .sdkmanrc
export GRAALVM_HOME=${SDKMAN_CANDIDATES_DIR}/java/22.3.r17-grl
```

# Pre-package
```shell
./mvnw package
```

# Generate config files
```shell
java \
  -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image \
  -jar target/sample-graal.jar \
  Main
```

# Modify config file
```shell
cat <<EOF > src/main/resources/META-INF/native-image/reflect-config.json
[
  {
    "name" : "com.example.footaku.graal.data.Content",
    "allDeclaredConstructors" : true,
    "allPublicConstructors" : true,
    "allDeclaredMethods" : true,
    "allPublicMethods" : true,
    "allDeclaredFields" : true,
    "allPublicFields" : true
  }
]
EOF
```

# Run
```shell
./mvnw package
./target/sample-graal ## -> Content{code=200, description='OK'}
```
