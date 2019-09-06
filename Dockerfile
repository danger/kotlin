FROM openjdk:12-jre

MAINTAINER Franco Meloni

LABEL "com.github.actions.name"="Danger Kotlin"
LABEL "com.github.actions.description"="Runs Kotlin Dangerfiles"
LABEL "com.github.actions.icon"="zap"
LABEL "com.github.actions.color"="blue"

# Install nodejs
RUN curl -sL https://deb.nodesource.com/setup_10.x |  bash -
RUN apt-get install -y nodejs

# Install danger-swift globally
RUN git clone https://github.com/danger/danger-kotlin.git _danger-kotlin
RUN cd _danger-swift && make install

# Run Danger Swift via Danger JS, allowing for custom args
ENTRYPOINT ["npx", "--package", "danger", "danger-kotlin", "ci"]
