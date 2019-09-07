#!/bin/sh
sudo chmod -R a+rwx /usr/local/
if ! [[ -x "$(command -v kotlinc)" ]]; then
    echo "Installing kotlin compiler 1.3.50"
    curl -o kotlin-compiler.zip -L https://github.com/JetBrains/kotlin/releases/download/v1.3.50/kotlin-compiler-1.3.50.zip
    unzip -d /usr/local/ kotlin-compiler.zip
    export PATH=/usr/local/kotlinc/bin:$PATH
    rm -rf kotlin-compiler.zip
fi
if ! [[ -x "$(command -v gradle)" ]]; then
    echo "Installing gradle 5.6.2"
    curl -o gradle.zip -L https://downloads.gradle-dn.com/distributions/gradle-5.6.2-bin.zip
    mkdir /opt/gradle
    unzip -d /opt/gradle gradle.zip
    export PATH=/opt/gradle/gradle-5.6.2/bin:$PATH
    rm -rf gradle.zip
fi
git clone https://github.com/danger/kotlin.git _danger-kotlin
cd _danger-kotlin && git checkout find_kotlinc && make install
cd ..
rm -rf _danger-kotlin
