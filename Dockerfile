FROM maven

MAINTAINER "Ivan Rodrigues"

CMD ["mvn", "clean", "install"]
ADD ./target/shortnner-url.jar /shortnner-url.jar
CMD ["java", "-jar", "/shortnner-url.jar"]

EXPOSE 8080
