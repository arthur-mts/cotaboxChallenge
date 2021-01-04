# Projeto COTABOX

[![Screenshot 1](https://raw.githubusercontent.com/arthur-mts/cotaboxChallenge/master/screenshots/1.png "Screenshot 1")]( https://raw.githubusercontent.com/arthur-mts/cotaboxChallenge/master/screenshots/1.png "Screenshot 1")
## :clipboard: Descrição
Esse projeto foi feito no processo seletivo da empresa Cotabox.  
Aproveitei a nova aplicação para aprender o framework JavaSpring Boot e a biblioteca de CSS Tailwind.

## 🧐 O que tem dentro?

### API

- [JavaSpring Boot](https://spring.io/projects/spring-boot)
- [MongoDB](https://github.com/mongodb/mongo)
- [Docker](https://docs.docker.com/compose/install/)
- [Junit](https://junit.org/junit5/)

### Frontend
- [ReactJS](https://pt-br.reactjs.org/)
- [Typescript](https://www.typescriptlang.org)
- [Axios](https://www.npmjs.com/package/axios)
- [TailwindCSS](https://tailwindcss.com/)


### Ferramentas de Linter
- [Eslint](https://eslint.org/)
- [Prettier](https://prettier.io/)

## :sparkles: Como executar?

#### API
- Instancie o banco de dados no Docker
- Instale as dependências e compile o projeto (ou execute o .jar disponível)
```sh
docker-compose --up
./mvnw install
./mvnw package
java -jar target/cotabox-backend-0.0.1-SNAPSHOT.jar
```
- Para testar use o comando a seguir:
```sh
./mvnw test
```

#### Frontend
- Instale as dependências com o Yarn (ou npm)
- Execute o projeto
```sh
yarn
yarn start
```
