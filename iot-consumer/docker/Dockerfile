FROM amazoncorretto:11.0.13

EXPOSE 8080

COPY target/iot-consumer-0.0.1-SNAPSHOT.jar iot-consumer.jar
COPY */start.sh /tmp/start.sh
RUN chmod +x /tmp/start.sh

ENTRYPOINT ["sh", "/tmp/start.sh"]