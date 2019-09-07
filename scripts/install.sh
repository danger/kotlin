#!/bin/sh
git clone https://github.com/danger/kotlin.git _danger-kotlin
sudo chmod -R a+rwx /usr/local/
cd _danger-kotlin && make install
cd ..
rm -rf _danger-kotlin