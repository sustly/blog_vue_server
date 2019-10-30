FROM hub.c.163.com/library/java:latest
VOLUME /tmp
ADD target/sentinel-dashboard.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]