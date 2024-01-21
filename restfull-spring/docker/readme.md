- @TODO https://docs.docker.com/network/network-tutorial-standalone/
- @TODO https://www.linkedin.com/pulse/learn-how-access-docker-container-its-name-from-host-renato-rodrigues/

### Application
- Build application
_mvn clean install_

Application paths:
- Local run: http://localhost:8080/hello-docker-app/hello
- Docker run: http://localhost:8081/hello-docker-app/hello

- Tomcat on docker: http://127.0.0.1:81/restfull-spring/swagger-ui/index.html

### Network
- Create network: _docker network create **docker-net**_
- Info about network:  _docker network inspect **docker-net**_
- Получение списка сетей: _docker network ls_

### Docker

 - Build Docker image: 
_docker build -t nixx/hello-docker ._

 - Run Docker:
_docker run --name HelloDocker -d -p 8081:8080 nixx/hello-docker_

 - Run Docker with volume mapper:
_docker run --name HelloDocker -v c:/tmp/logs:/logs -d -h=webserver -p 8081:8080 nixx/hello-docker_

 - _docker run --name HelloDocker -v c:/tmp/logs:/logs --network **docker-net** -d -h=webserver -p 8081:8080 nixx/hello-docker_

-d - detached mode 

 - Stop Docker:
_docker stop HelloDocker_

 - Remove Docker:
_docker rm HelloDocker_

 - Active list:
_docker ps > t_

 - Console for Application
_docker exec -it HelloDocker /bin/sh_

- Получение информации о контейнерах: _docker ps_

- Получение информации о image: _docker image ls_ 


