FROM openjdk:11-jre
WORKDIR D: /3 курс/ТРСПО/lab3/order/out/artifacts/order_jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/app.jar"]