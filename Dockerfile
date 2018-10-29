FROM openjdk:8
COPY ./target/simple_drop_micro-1.0.1-SNAPSHOT.jar /usr/src/simple_drop_micro/
COPY ./tell-time.yml /usr/src/simple_drop_micro/
WORKDIR /usr/src/simple_drop_micro
EXPOSE 8080
CMD java -jar simple_drop_micro-1.0.1-SNAPSHOT.jar server tell-time.yml
