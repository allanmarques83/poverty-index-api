FROM maven:3.8.4-amazoncorretto-17 AS builder

COPY src /usr/app/src
COPY pom.xml /usr/app/

WORKDIR /usr/app/

RUN mvn -f pom.xml -B de.qaware.maven:go-offline-maven-plugin:resolve-dependencies

RUN mvn -B de.qaware.maven:go-offline-maven-plugin:resolve-dependencies clean package -DskipTests

FROM amazoncorretto:17 as prod

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en'

COPY --chown=185 --from=builder /usr/app/target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 --from=builder /usr/app/target/quarkus-app/*.jar /deployments/
COPY --chown=185 --from=builder /usr/app/target/quarkus-app/app/ /deployments/app/
COPY --chown=185 --from=builder /usr/app/target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185

CMD java -Dquarkus.http.host=0.0.0.0 -Dquarkus.http.port=8080 -Djava.util.logging.manager=org.jboss.logmanager.LogManager -jar /deployments/quarkus-run.jar
