FROM openjdk:8-jdk-alpine
#MAINTAINER com.robsonmrsp
COPY target/netflics-1.0-SNAPSHOT.war 	netflics-1.0-SNAPSHOT.war 
ENTRYPOINT ["java","-jar","/netflics-1.0-SNAPSHOT.war"]