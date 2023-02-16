copy .\..\..\target\restfull-spring.war

docker stop ResfullSpring

docker rm ResfullSpring
                                
docker build -t nixx/restfull-spring .

docker run --name ResfullSpring -d -h=webserver -p 8081:8080 nixx/restfull-spring
