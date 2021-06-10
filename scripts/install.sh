#!/bin/sh

sudo -v && sudo="true" || sudo=""

if ! [[ -x "$(command -v danger)" ]]; then
	if ! [[ -x "$(command -v npm)" ]]; then
		echo "Please install node js"
		exit 1
	fi

	echo "Installing danger"

	if [[ -n "$sudo" ]]; then
		sudo npm install -g danger
	else
		npm install -g danger
	fi
fi

if [[ -n "$sudo" && "$OSTYPE" != "darwin"* ]]; then
	sudo chmod -R a+rwx /usr/local/
fi

if ! [[ -x "$(command -v kotlinc)" ]]; then
    echo "Installing kotlin compiler 1.4.31"
    curl -o kotlin-compiler.zip -L https://github.com/JetBrains/kotlin/releases/download/v1.4.31/kotlin-compiler-1.4.31.zip
    unzip -d /usr/local/ kotlin-compiler.zip
    echo 'export PATH=/usr/local/kotlinc/bin:$PATH' >> ~/.bash_profile
    rm -rf kotlin-compiler.zip
fi

if ! [[ -x "$(command -v gradle)" ]]; then
    echo "Installing gradle 5.6.2"
    curl -o gradle.zip -L https://downloads.gradle-dn.com/distributions/gradle-5.6.2-bin.zip
    mkdir /opt/gradle
    unzip -d /opt/gradle gradle.zip
    echo 'export PATH=/opt/gradle/gradle-5.6.2/bin:$PATH' >> ~/.bash_profile
    rm -rf gradle.zip
fi

LATEST_STABLE_VERSION=1.0.0-beta.2
git clone https://github.com/danger/kotlin.git --branch $LATEST_STABLE_VERSION --depth 1 _danger-kotlin
cd _danger-kotlin && make install
cd ..
rm -rf _danger-kotlin
