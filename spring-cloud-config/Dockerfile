FROM openjdk:11-oraclelinux8

ARG JAR_FILE

RUN mkdir -p /apps
COPY ./target/${JAR_FILE} /apps/app.jar
COPY ./start.sh /apps/start.sh
COPY ./configs /apps/configs
RUN chmod +x /apps/start.sh

CMD ["/apps/start.sh"]