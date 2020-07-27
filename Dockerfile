FROM nexus:8084/openjdk/openjdk-8-rhel8:v1
USER root
RUN mkdir -p /home/jboss/src/app
WORKDIR /home/jboss/src/app
ENV JAVA_HOME /etc/alternatives/jre
COPY --chown=1001:0 auth-dev-cloudb-sat-gob-mx.crt /home/jboss/src/app
ADD target/Ms-sat-covol-zuul-0.0.1-SNAPSHOT.jar /home/jboss/src/app
RUN keytool -importcert -alias authDevCloudb -keystore "$JAVA_HOME"/lib/security/cacerts -storePass changeit -file auth-dev-cloudb-sat-gob-mx.crt -noprompt
# EXPOSE 8780
EXPOSE 8080
USER jboss
CMD ["java", "-jar","Ms-sat-covol-zuul-0.0.1-SNAPSHOT.jar"]  