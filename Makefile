TOOL_NAME = danger-kotlin
VERSION = 0.1.0

PREFIX = /usr/local
INSTALL_PATH = $(PREFIX)/bin/$(TOOL_NAME)
BUILD_PATH = danger-kotlin/build/bin/runner/main/release/executable/$(TOOL_NAME).kexe
LIB_INSTALL_PATH = $(PREFIX)/lib/danger
TAR_FILENAME = $(TOOL_NAME)-$(VERSION).tar.gz

install: build
	mkdir -p $(PREFIX)/bin
	mkdir -p $(PREFIX)/lib/danger
	cp -f $(BUILD_PATH) $(INSTALL_PATH)

build:
	mvn dependency:get -Dartifact=com.squareup.moshi:moshi-kotlin:1.8.0
	gradle publishToMavenLocal -p danger-kotlin-library
	gradle build -p danger-kotlin

uninstall:
	rm -f $(INSTALL_PATH)
	rm -rf ~/.m2/repository/com/danger/danger-kotlin-library/
