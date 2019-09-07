FROM gradle:5.6.2-jdk8

MAINTAINER Franco Meloni

LABEL "com.github.actions.name"="Danger Kotlin"
LABEL "com.github.actions.description"="Runs Kotlin Dangerfiles"
LABEL "com.github.actions.icon"="zap"
LABEL "com.github.actions.color"="blue"

# Install dependencies
RUN curl -sL https://deb.nodesource.com/setup_10.x |  bash -
RUN apt-get install -y nodejs make zip
RUN curl -s https://get.sdkman.io | bash
RUN sdk install kotlin

# Install danger-swift globally
RUN git clone https://github.com/danger/kotlin.git _danger-kotlin
RUN cd _danger-kotlin && make install

# Run Danger Swift via Danger JS, allowing for custom args
ENTRYPOINT ["npx", "--package", "danger", "danger-kotlin", "ci"]
