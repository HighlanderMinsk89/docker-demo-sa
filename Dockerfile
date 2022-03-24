FROM openjdk:17-jdk-alpine 

RUN apk update && apk upgrade && adduser -D runner && mkdir -p /opt/tmp && chmod 777 /opt/tmp && chown runner:runner /opt/tmp 

WORKDIR /opt/home 

COPY target/demo-0.0.1-SNAPSHOT.jar /opt/home/core.jar 

RUN chmod 666 /opt/home/core.jar 


USER runner 

 
ENV TZ="America/New_York" 

EXPOSE 8080 

ENTRYPOINT ["java", "-Djava.io.tmpdir=/opt/tmp", "-Xms1024M", "-Xmx1024M", "-jar", "/opt/home/core.jar"] 

CMD ["-h"] 