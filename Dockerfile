FROM gradle:5.6.2-jdk8

MAINTAINER Franco Meloni

LABEL "com.github.actions.name"="Danger Kotlin"
LABEL "com.github.actions.description"="Runs Kotlin Dangerfiles"
LABEL "com.github.actions.icon"="zap"
LABEL "com.github.actions.color"="blue"

# Install dependencies
RUN curl -sL https://deb.nodesource.com/setup_10.x |  bash -
RUN apt-get install -y nodejs make zip

RUN cd /usr/lib && \
    wget -q https://github.com/JetBrains/kotlin/releases/download/v1.3.50/kotlin-compiler-1.3.50.zip && \
    unzip kotlin-compiler-*.zip && \
    rm kotlin-compiler-*.zip

ENV PATH $PATH:/usr/lib/kotlinc/bin

RUN kotlinc -version

# Install danger-swift globally
RUN git clone https://github.com/danger/kotlin.git _danger-kotlin
RUN cd _danger-kotlin && make install

# Run Danger Swift via Danger JS, allowing for custom args
ENTRYPOINT ["npx", "--package", "danger", "danger-swift", "ci"]
