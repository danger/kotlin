FROM gradle:7.4.2-jdk8

MAINTAINER Franco Meloni

LABEL "com.github.actions.name"="Danger Kotlin"
LABEL "com.github.actions.description"="Runs Kotlin Dangerfiles"
LABEL "com.github.actions.icon"="zap"
LABEL "com.github.actions.color"="blue"

# Install dependencies
RUN apt-get update && \
    apt-get install -y ca-certificates && \
    curl -sL https://deb.nodesource.com/setup_14.x |  bash - && \
    apt-get install -y make zip nodejs && \
    npm install -g danger

# Install danger-kotlin globally
COPY . /usr/local/_danger-kotlin
RUN cd /usr/lib && \
    wget -q https://github.com/JetBrains/kotlin/releases/download/v1.6.21/kotlin-compiler-1.6.21.zip && \
    unzip kotlin-compiler-*.zip && \
    rm kotlin-compiler-*.zip && \
    cd /usr/local/_danger-kotlin && \
    make install && \
    rm -rf /usr/local/_danger-kotlin

ENV PATH $PATH:/usr/lib/kotlinc/bin

# Run Danger Kotlin via Danger JS, allowing for custom args
ENTRYPOINT ["danger-kotlin", "ci"]
