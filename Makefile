TOOL_NAME = danger-kotlin
VERSION = 0.1.0
OS_TARGET = $(./gradlew :danger-kotlin:osName | grep OS_TARGET)


PREFIX = /usr/local
INSTALL_PATH = $(PREFIX)/bin/$(TOOL_NAME)
BUILD_PATH = danger-kotlin/build/konan/bin/${OS_TARGET}/$(TOOL_NAME).kexe
LIB_INSTALL_PATH = $(PREFIX)/lib/danger
TAR_FILENAME = $(TOOL_NAME)-$(VERSION).tar.gz

install: build
	mkdir -p $(PREFIX)/bin
	mkdir -p $(PREFIX)/lib/danger
	cp -f $(BUILD_PATH) $(INSTALL_PATH)

build:
	./gradlew :danger-kotlin-library:publishToMavenLocal
	./gradlew :danger-kotlin:build

uninstall:
	rm -f $(INSTALL_PATH)
	rm -rf ~/.m2/repository/com/danger/danger-kotlin-library/