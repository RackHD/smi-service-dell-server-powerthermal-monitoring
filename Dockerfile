FROM openjdk:8-jre
VOLUME /tmp
ADD build/libs/service-powerthermal-monitoring*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

