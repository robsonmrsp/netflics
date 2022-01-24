FROM adoptopenjdk:11-jdk-hotspot-bionic
LABEL maintainer "Tim Brust <github@timbrust.de>"

ARG MAVEN_VERSION=3.8.4
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://dlcdn.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries

ARG REFRESHED_AT
ENV REFRESHED_AT $REFRESHED_AT

SHELL ["/bin/bash", "-o", "pipefail", "-c"]

#installing node.js
RUN curl -sL https://deb.nodesource.com/setup_12.x | bash - \
	&& curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add - \
	&& echo "deb https://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list \
	&& apt-get update -qq \
	&& apt-get install -qq --no-install-recommends \
	nodejs \
	yarn \
	&& apt-get upgrade -qq \
	&& rm -rf /var/lib/apt/lists/*


#installing maven
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
	&& curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
	&& tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
	&& rm -f /tmp/apache-maven.tar.gz \
	&& ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
RUN mvn --version

RUN mkdir /app
COPY . /app/
WORKDIR /app/

# just to make sure this image have all the dependencies 
RUN mvn -q eclipse:eclipse

EXPOSE 9991

