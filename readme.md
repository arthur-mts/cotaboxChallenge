# Projeto COTABOX

## Frontend
- O frontend do projeto está dentro da pasta web.
- Nesse projeto foram usados: ReactJS, Tailwind, ReactVis e Axios.
- Para rodar o frontend, basta entrar na pasta web e executar os comandos:

```sh
yarn
yarn start
```

## Backend
- O backend do projeto está dentro da pasta backend.
- Nesse projeto foram usados: DockerCompose(Para subir o banco de dados),MongoDB, Java Spring Boot, Lombook e Jackson.
- Para rodar o backend você precisa rodar os comandos dentro da pasta backend:
```sh
docker-compose --up
./mvnw install
./mvnw package
java -jar target/cotabox-backend-0.0.1-SNAPSHOT.jar
```
- Os testes foram feitos com Junit 5.0, para executalos é so rodar:
```sh
./mvnw test
```
