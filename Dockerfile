FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=userscrud.jar
COPY ${JAR_FILE} app.jar
CMD ["java","-jar","/app.jar"]