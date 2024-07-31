FROM azul/zulu-openjdk:11-latest
# FROM eclipse-temurin:11-jdk

COPY cloud_build/csap.sh /csap.sh
RUN chmod 700 /csap.sh && /csap.sh

ENV TZ=Asia/Seoul
RUN apt-get install -y tzdata
COPY target/portal.war portal.war

CMD ["java", "-jar", "/portal.war"]

