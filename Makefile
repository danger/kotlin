TOOL_NAME = danger-kotlin
VERSION = 0.1.0

PREFIX = /usr/local
INSTALL_PATH = $(PREFIX)/bin/$(TOOL_NAME)
BUILD_PATH = danger-kotlin/build/bin/runner/releaseExecutable/$(TOOL_NAME).kexe
LIB_INSTALL_PATH = $(PREFIX)/lib/danger
TAR_FILENAME = $(TOOL_NAME)-$(VERSION).tar.gz

install: build
	mkdir -p $(PREFIX)/bin
	mkdir -p $(PREFIX)/lib/danger
	cp -f $(BUILD_PATH) $(INSTALL_PATH)
	cp -f danger-kotlin-library/build/libs/danger-kotlin.jar $(LIB_INSTALL_PATH)/danger-kotlin.jar
	curl https://repo1.maven.org/maven2/org/jetbrains/kotlin/kotlin-main-kts/1.3.60/kotlin-main-kts-1.3.60.jar -o $(LIB_INSTALL_PATH)/kotlin-main-kts.jar

build:
	gradle wrapper
	./gradlew shadowJar -p danger-kotlin-library
	./gradlew build -p danger-kotlin

uninstall:
	rm -rf $(INSTALL_PATH)
	rm -f $(LIB_INSTALL_PATH)/danger-kotlin.jar
	rm -f $(LIB_INSTALL_PATH)/kotlin-main-kts.jar
