FROM openjdk:11-jre-slim

ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

WORKDIR application

COPY  ./././target/mercadolibre-mutants-1.0.0.jar ./

ENTRYPOINT ["java", "-jar", "mercadolibre-mutants-1.0.0.jar"]