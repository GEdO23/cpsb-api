# Cutcute Petshop Spring Boot API

[Read in portuguese](README-pt.md) 

[Read in english](README.md)

[//]: #TODO (Add a description to the project)
[//]: #TODO (Add the Cutcute Petshop logo)

## Prerequisites
To run the project, you need to have installed:
- [Docker](https://www.docker.com/)

## How to run the project with Docker
Follow the instructions below to run the container:

### 1. Clone the repository
```bash
git clone https://github.com/GEdO23/cpsb-api.git
cd cpsb-api
```

### 2. Build the image
```bash
docker build -t cpsb-api .
```
This command will:
- Build the image from the Dockerfile (`-t cpsb-api`)
- Use the context of the current directory (where the Dockerfile is located)
- Name the image as `cpsb-api`

### 3. Run the container
```bash
docker run -d --name cpsb-api -p 8080:8080 cpsb-api
```
This command will:
- Run the container in the background (`-d`)
- Name the container as `cpsb-api` (`--name cpsb-api`)
- Map port 8080 of the host to port 8080 of the container (`-p 8080:8080`)

### 4. Access the API
Access the API at [http://localhost:8080](http://localhost:8080)

## Technologies used
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Docker](https://www.docker.com/)
- [H2](https://www.h2database.com/html/main.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Bootstrap](https://getbootstrap.com/)
