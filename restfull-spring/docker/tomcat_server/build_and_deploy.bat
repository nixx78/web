copy .\..\..\target\restfull-spring.war

docker stop TomcatWithRestfullSpring
docker rm TomcatWithRestfullSpring
docker build -t nixx/tomcat_with_restfull_spring .

docker run --name TomcatWithRestfullSpring -d -p 81:8080 nixx/tomcat_with_restfull_spring
