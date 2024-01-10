
# Worst Movie Awards Winners

EN-US

The main goal of this application is to manage the record of movies that won or not a award and to realize a comparison between the producers of these movies that had the max and minimun interval since they won their first award to their most recent one.

For the sake of simplicity, this project does not have every single CRUD operations for Producers and Studios. All the objects are managed throug the CRUD of Movies.

PT-BR

O objetivo dessa aplicação gerenciar o cadastro de filmes que venceram ou não alguma premiação e realizar um comparativo entre os produtores desses filmes que tiveram o maior e menor intervalo desde o seu primeiro prêmio até seu prêmio mais atual.

Para critérios de simplificação, não foram construidos completamente todas as etapadas de CRUD dos Produtores e Estudios. Todos os objetos são gerenciados através do CRUD de filmes.


## Autors

- [@Dechechi](https://www.github.com/Dechechi)


## Running locally

Clone the project

```bash
  git clone https://github.com/Dechechi/golden_awards_worst_movies/tree/development
```

Install dependencies using Maven

```bash
  mvn clean -U install
```

Run the application with java (Make sure to use java version 17 or greater)

```bash
  java -jar golden-awards-worst-movies-application\target\golden-awards-worst-movies-application-0.0.1-SNAPSHOT.jar
```


## API Documentation

### Movie

#### Returns all saved Movies

```http
  GET localhost:8080/api/v1/movie
```

#### Returns a specific Movie by its id

```http
  GET localhost:8080/api/v1/movie/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Required**. Desired Movies ID |

#### Creates a movie

```http
  POST localhost:8080/api/v1/movie
```
##### JSON Body:

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `year`      | `string` | **Required**. Movie release year |
| `title`      | `string` | **Required**. Movie title |
| `producers`      | `string` | **Required**. List of producers separated by comma "," |
| `studios`      | `string` | **Required**. List of studios separated by comma "," |
| `winner`      | `string` | **Required**. If the movie won a award that year or not (use "true" or "false") |

#### Updates a movie

```http
  PUT localhost:8080/api/v1/movie/${id}
```
##### JSON Body:
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `year`      | `string` | **Required**. Movie release year |
| `title`      | `string` | **Required**. Movie title |
| `producers`      | `string` | **Required**. List of producers separated by comma "," |
| `studios`      | `string` | **Required**. List of studios separated by comma "," |
| `winner`      | `string` | **Required**. If the movie won a award that year or not (use "true" or "false") |

#### Delete a specific Movie by its id

```http
  DELETE localhost:8080/api/v1/movie/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Required**. Desired Movies ID |


### Award

#### Returns the max and min interval producers wich got awards

```http
  GET localhost:8080/api/v1/awards
```

### A postman collection with examples have been provided in the root directory of the project
## Environment

If you desire to change any variable to run your code locally, just update the application.yml file in the main root directory

The most important value you must set up, is the csv file path, witch is recommended to be left inside the Resources folder of the golden-awards-worst-movies-application

If you change the location or change the file name, make sure to update the following variable

`reader.csv.path = "/movielist (2).csv"`

