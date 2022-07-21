# DisneyApiRest

This app was created with Bootify.io - more documentation [can be found here](https://bootify.io/docs/). Feel free to contact us for further questions.

## CHALLENGE BACKEND - Java Spring Boot (API)

- [Challenge](https://drive.google.com/file/d/1ICHCzERR_tC9yB9crJyxVoqtNXsduOky/view)

## Usage

1.- Run SpringBoot App
2.- Front -> http://localhost:8080/
3.- Swagger -> http://localhost:8080/swagger-ui/index.html

## EndPoints

movie-resource

GET
/api/movies/{id}

PUT
/api/movies/{id}

DELETE
/api/movies/{id}

GET
/api/movies

POST
/api/movies

POST
/api/movies/{idMovie}/characters/{idCharacter}

DELETE
/api/movies/{idMovie}/characters/{idCharacter}
gender-resource

GET
/api/genders/{id}

PUT
/api/genders/{id}

DELETE
/api/genders/{id}

GET
/api/genders

POST
/api/genders
character-resource

GET
/api/characters/{id}

PUT
/api/characters/{id}

DELETE
/api/characters/{id}

GET
/api/characters

POST
/api/characters
auth-resource

POST
/api/auth/register

POST
/api/auth/login

## Development

During development it is recommended to use the profile `local`. In IntelliJ, `-Dspring.profiles.active=local` can be added in the VM options of the Run Configuration after enabling this property in "Modify options".

Update your local database connection in `application.properties` or create your own `application-local.properties` file to override settings for development.

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
mvnw clean package
```

The application can then be started with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/disney-api-rest-0.0.1-SNAPSHOT.jar
```

## Further readings

- [Maven docs](https://maven.apache.org/guides/index.html)
- [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Thymeleaf docs](https://www.thymeleaf.org/documentation.html)
- [Bootstrap docs](https://getbootstrap.com/docs/5.1/getting-started/introduction/)
