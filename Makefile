TOOL_NAME = danger-kotlin
VERSION = 0.6.1

PREFIX = /usr/local
INSTALL_PATH = $(PREFIX)/bin/$(TOOL_NAME)
BUILD_PATH = danger-kotlin/build/bin/runner/releaseExecutable/$(TOOL_NAME).kexe
LIB_INSTALL_PATH = $(PREFIX)/lib/danger
TAR_FILENAME = $(TOOL_NAME)-$(VERSION).tar.gz

install: build
	mkdir -p $(PREFIX)/bin
	mkdir -p $(LIB_INSTALL_PATH)
	cp -f $(BUILD_PATH) $(INSTALL_PATH)
	cp -f danger-kotlin-library/build/libs/danger-kotlin.jar $(LIB_INSTALL_PATH)/danger-kotlin.jar

build:
	./gradlew shadowJar -p danger-kotlin-library
	./gradlew build -p danger-kotlin-kts
	./gradlew build -p danger-kotlin

uninstall:
	rm -rf $(INSTALL_PATH)
	rm -f $(LIB_INSTALL_PATH)/danger-kotlin.jar
