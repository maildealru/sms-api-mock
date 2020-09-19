FROM openjdk:14-jdk as base
WORKDIR /tmp
COPY . .
RUN export VERSION=$(./gradlew -q printVersion) \
        && ./gradlew build && mv /tmp/build/libs/sms-api-mock-${VERSION}.jar /tmp/service.jar

FROM openjdk:14
WORKDIR /usr/local/lib
COPY --from=base "/tmp/service.jar" .
ENTRYPOINT java -jar -Dspring.profiles.active=prod ./service.jar
EXPOSE 80
