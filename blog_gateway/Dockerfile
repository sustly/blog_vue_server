FROM hub.c.163.com/library/java:latest
VOLUME /tmp
ADD target/blog_server.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]