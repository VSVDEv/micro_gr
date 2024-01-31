FROM openjdk:11
ADD build/libs/*.jar  ./
ENTRYPOINT ["java","-jar","/micro_gr.jar"]
EXPOSE 8080
