TOOL_NAME = danger-kotlin
VERSION = 0.1.0

PREFIX = /usr/local
INSTALL_PATH = $(PREFIX)/bin/$(TOOL_NAME)
BUILD_PATH = danger-kotlin/build/bin/macos/main/release/executable/$(TOOL_NAME).kexe
LIB_INSTALL_PATH = $(PREFIX)/lib/danger
TAR_FILENAME = $(TOOL_NAME)-$(VERSION).tar.gz

install: build
	mkdir -p $(PREFIX)/bin
	mkdir -p $(PREFIX)/lib/danger
	cp -f $(BUILD_PATH) $(INSTALL_PATH)

build:
	gradle publishToMavenLocal -p danger-kotlin-library
	gradle build -p danger-kotlin

uninstall:
	rm -f $(INSTALL_PATH)
	rm -rf ~/.m2/repository/com/danger/danger-kotlin-library/