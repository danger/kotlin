#!/bin/sh
git clone https://github.com/danger/kotlin.git _danger-kotlin
cd _danger-kotlin && make install
cd ..
rm -rf _danger-kotlin