FROM openjdk:11-jdk
ARG TARGET_PATH=target/inventory*.jar
RUN mkdir -p /app
COPY ${TARGET_PATH} /app/app.jar
RUN chmod 444 /app/app.jar
CMD java $JAVA_OPTS -jar /app/app.jar