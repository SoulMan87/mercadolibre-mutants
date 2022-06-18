FROM openjdk:11-jre-slim as builder
WORKDIR application
ADD target/mercadolibre-mutants-1.0.0.jar ./
RUN java -Djarmode=layertools -jar mercadolibre-mutants-1.0.0.jar extract

FROM openjdk:11-jre-slim

WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]