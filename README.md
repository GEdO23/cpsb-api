# Cutcute Petshop Spring Boot API

[//]: #TODO (Add a description of the project)

## Pré-requisitos
Para executar o projeto é necessário ter instalado:
- [Docker](https://www.docker.com/)

## Como executar o projeto com Docker
Siga as instruções abaixo para executar o container:

### 1. Clonar o repositório
```bash
git clone https://github.com/GEdO23/cpsb-api.git
cd cpsb-api
```

### 2. Construir a imagem
```bash
docker build -t cpsb-api .
```
Este comando irá:
- Construir a imagem a partir do Dockerfile (`-t cpsb-api`)
- Utilizar o contexto do diretório atual (onde está o Dockerfile)
- Nomear a imagem como `cpsb-api`

### 3. Executar o container
```bash
docker run -d --name cpsb-api -p 8080:8080 cpsb-api
```
Este comando irá:
- Executar o container em background (`-d`)
- Nomear o container como `cpsb-api` (`--name cpsb-api`)
- Mapear a porta 8080 do host para a porta 8080 do container (`-p 8080:8080`)

### 4. Acessar a API
Acesse a API em [http://localhost:8080](http://localhost:8080)

## Tecnologias utilizadas
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Docker](https://www.docker.com/)
- [H2](https://www.h2database.com/html/main.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Bootstrap](https://getbootstrap.com/)
