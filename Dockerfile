FROM openjdk:8
ADD myfile/docker-movie-catalogue.jar docker-movie-catalogue.jar
EXPOSE 8100
ENTRYPOINT ["java","-jar","docker-movie-catalogue.jar"] 