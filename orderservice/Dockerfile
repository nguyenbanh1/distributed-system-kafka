FROM adoptopenjdk/openjdk8

ENV TZ=Asia/Ho_Chi_Minh

COPY build/libs/orderservice-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8090/tcp

ENTRYPOINT [ "java", "-jar", "app.jar" ]