FROM amazoncorretto:11.0.13

EXPOSE 8080

COPY target/iot-visualizer-0.0.1-SNAPSHOT.jar iot-visualizer.jar
COPY */start.sh /tmp/start.sh
RUN chmod +x /tmp/start.sh

ENTRYPOINT ["sh", "/tmp/start.sh"]