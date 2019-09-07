#!/bin/sh
if ! [ -x "$(command -v kotlinc)" ]; then
    echo "Installing kotlin compiler 1.3.50"
    curl -L https://github.com/JetBrains/kotlin/releases/download/v1.3.50/kotlin-compiler-1.3.50.zip
    mkdir /opt/kotlin
    unzip -d /opt/kotlin kotlin-compiler-1.3.50.zip
    export PATH=$PATH:/opt/kotlin
    rm -rf kotlin-compiler-1.3.50.zip
fi
if ! [ -x "$(command -v gradle)" ]; then
    echo "Installing gradle 5.6.2"
    curl -L https://downloads.gradle-dn.com/distributions/gradle-5.6.2-bin.zip
    mkdir /opt/gradle
    unzip -d /opt/gradle gradle-5.6.2-bin.zip
    export PATH=$PATH:/opt/gradle/gradle-5.6.2/bin
    rm -rf gradle-5.6.2-bin.zip
fi
git clone https://github.com/danger/kotlin.git _danger-kotlin
sudo chmod -R a+rwx /usr/local/
cd _danger-kotlin && make install
cd ..
rm -rf _danger-kotlin