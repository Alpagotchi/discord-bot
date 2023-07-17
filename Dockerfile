FROM maven:3.9.3 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17
WORKDIR /home/app
ARG TOKEN=""
ENV TOKEN=$TOKEN
ARG POSTGRES_USER=""
ENV POSTGRES_USER=$POSTGRES_USER
ARG POSTGRES_PASSWORD=""
ENV POSTGRES_PASSWORD=$POSTGRES_PASSWORD
ARG POSTGRES_URL=""
ENV POSTGRES_URL=$POSTGRES_URL
ENV DEV_ID=483012399893577729
COPY --from=build /home/app/target/Alpagotchi-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]