FROM openjdk:12-alpine

MAINTAINER Franco Meloni

LABEL "com.github.actions.name"="Danger Kotlin"
LABEL "com.github.actions.description"="Runs Kotlin Dangerfiles"
LABEL "com.github.actions.icon"="zap"
LABEL "com.github.actions.color"="blue"

# Install nodejs
RUN apk add --no-cache bash git openssh nodejs cmake

# Install danger-swift globally
RUN git clone https://github.com/danger/kotlin.git _danger-kotlin
RUN cd _danger-kotlin && make install

# Run Danger Swift via Danger JS, allowing for custom args
ENTRYPOINT ["npx", "--package", "danger", "danger-kotlin", "ci"]
