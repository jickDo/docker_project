FROM openjdk:17
RUN mkdir /var/myapp
WORKDIR /var/myapp


ARG JAR_FILE=limjickchan-0.0.1-SNAPSHOT.jar
COPY ./build/libs/${JAR_FILE} abb.jar

# JAR 파일을 실행합니다.
ENTRYPOINT ["java","-jar","abb.jar"]

EXPOSE 8080

