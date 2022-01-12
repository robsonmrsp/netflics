FROM openjdk:8-jdk-alpine
#MAINTAINER com.robsonmrsp
WORKDIR /
RUN mvn clean package
COPY target/netflics-1.0-SNAPSHOT.war 	netflics-1.0-SNAPSHOT.war 
ENTRYPOINT ["java","-jar","/netflics-1.0-SNAPSHOT.war"]