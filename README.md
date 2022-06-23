#  Proyecto deteccion de mutantes


## Especificaciones:
El proyecto consiste en una API REST que determina si un adn humano es un mutante basandose en mas de una determinada secuencia de genes.
## Alcance:
* El API REST contará con dos servicios web.
* Un servicio web será "/mutants/", que se encargará de determinar si un adn humano enviado como json es mutante o no.
  En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un 403-Forbidden
* El otro servicio web será "/stats" que se encargará devolver un Json con las estadísticas de las verificaciones de ADN
  Por ejemplo: {"countMutantDna":40, "countMutantDna":100, "ratio":0.4}
* Se guardará el request y un boolean si es mutante o no en un base de datos.
* Se creará test unitarios y de integración.


## Tecnologías usadas:
* El proyecto está desarrollado en 11
* Se utiliza Maven para la gestión de dependencias, build y packaging.
* Para el Backend se utiliza Spring Boot versión 2.7.0.
* Se utiliza la base de datos PostgreSQL.
* Para los tests se uso JUnit y Mockito.
* Se utilizó Heroku como cloud computing.
* Se utiliza las herramientas de Docker para crear images y contenedores de la aplicación
* OpenApi y SwaggerUI



## Acceso a la API REST productivo:

* Para entrar al servicio “/mutants/” se debe enviar una secuencia ADN mediante un HTTP POST a esta URL:</br>

https://m-mutants.herokuapp.com/swagger-ui/index.html <br />

Para más facilidad de uso se optó por utilizar [Swagger UI](https://swagger.io/)

Swagger permite acceder de manera más fácil a los endpoints: <br />

Se debe enviar el siguiente Json en el endpoint mutant-controller <br />

![SwaggerImage](/images/swagger%20api.png)


```
{ 
   "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] 
}
```
Una vez se envíe el primer dna se podrá ver por medio del endpoint statistics-controller la cuenta </br>
de los mutantes y no mutantes: <br />

```
{
  "countMutantDna": 1,
  "countHumanDna": 0,
  "ratio": 1
}
```


## Para poder levantar la aplicación en local se debe seguir lo siguiente:

### Requisitos:
* La aplicación cuenta con el motor de bases de datos en memoria H2.
* Tener mvn instalado en su ambiente, 3.6.1 o posterior.
* Tener un JDK versión 11.
* Se recomienda tener Git.

### Configuracion:
1. En un directorio clonar el proyecto
```
git clone https://github.com/SoulMan87/mercadolibre-mutants
```
Una ves se tenga el repositorio se debe cambiar de rama de "main" a "master"

```
git checkout master
```


2. Posicionarse en el directorio del proyecto y correr ```mvn clean install``` desde una terminal para descargar todas sus dependencias.

### Ejecucion:
- Posicionarse en el directorio del proyecto y ejecutar ```mvn spring-boot:run``` desde una terminal.

## Test:
Para la ejecucion de los test automaticos utilice JUnit y use framework Mockito para realizar pruebas .

- Para ejecutar las pruebas se puede ir al directorio del proyecto y correr el siguiente comando: ```mvn test```

## Levantar el ambiente solamente con docker

### Requisitos:
* Se debe tener tener instalado docker version 20.10.17
* Se debe tener mvn instalado en su ambiente, 3.6.1 o posterior.
* Se debe tener un JDK versión 11.
* Se debe tener instalado el Git.

### Configuracion:
1. En un directorio clonar el proyecto
```
git clone https://github.com/SoulMan87/mercadolibre-mutants
```
Una ves se tenga el repositorio se debe cambiar de rama de "main" a "master"

2. Posicionarse en el directorio del proyecto y correr ```mvn clean install -Dmaven.test.skip=true``` desde una terminal para descargar todas sus dependencias.

3. Posicionarse en el directorio del proyecto descargado y ejecutar en un cmd el siguiente comando:
```
mvn spring-boot:build-image
```


