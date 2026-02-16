# Ecommerce API - Prueba Técnica

API REST reactiva para la gestión de productos y pedidos, desarrollada
con **Java 17**, **Spring Boot (WebFlux)** y **Maven**.

------------------------------------------------------------------------

## Tecnologías utilizadas

-   Java 17
-   Spring Boot
-   Spring WebFlux (programación reactiva)
-   Spring Data R2DBC
-   Maven
-   Swagger UI (documentación interactiva)
-   Base de datos relacional (H2, configurable)

------------------------------------------------------------------------

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

-   Java 17
-   Maven 3.8+
-   Base de datos configurada (según `application.yml`)

------------------------------------------------------------------------

## Cómo levantar el proyecto

1.  Clonar el repositorio:

``` bash
git clone https://github.com/jaragon-y/interbank-reto-tecnico.git
cd /interbank-reto-tecnico/ecommerce-api
```

2.  Compilar el proyecto:

``` bash
mvn clean install
```

3.  Ejecutar la aplicación:

``` bash
mvn spring-boot:run
```

O si prefieres ejecutar el jar generado:

``` bash
java -jar target/nombre-del-jar.jar
```

------------------------------------------------------------------------

## URL Base

Por defecto la aplicación corre en:

    http://localhost:9292/v1/api/ecommerce-api

*(Si el puerto cambia, revisar `application.yml`)*

------------------------------------------------------------------------

## Documentación y pruebas

Una vez levantada la aplicación, puedes acceder a la documentación
interactiva desde:

    http://localhost:9292/v1/api/ecommerce-api/swagger-ui/index.html#/

Desde Swagger UI puedes:

-   Visualizar todos los endpoints disponibles
-   Probar los endpoints directamente
-   Enviar requests sin necesidad de herramientas externas

------------------------------------------------------------------------

## Cómo probar la API

1.  Abrir Swagger UI en el navegador: http://localhost:9292/v1/api/ecommerce-api/swagger-ui/index.html#/.
2.  Seleccionar el endpoint que deseas probar.
3.  Hacer clic en **"Try it out"**.
4.  Completar los datos requeridos en el request.
5.  Ejecutar la petición y revisar la respuesta.

------------------------------------------------------------------------

## Arquitectura

La aplicación está desarrollada bajo principios de:

-   Arquitectura en capas (Controller ? Service ? Repository)
-   Programación reactiva utilizando `Mono` y `Flux`
-   Separación de responsabilidades
-   Manejo centralizado de errores
-   Control de concurrencia mediante versionado (Optimistic Locking)

------------------------------------------------------------------------

## Consideraciones importantes

-   El stock se valida al confirmar un pedido.
-   Se implementa control de concurrencia para evitar inconsistencias en
    actualizaciones simultáneas.
-   Las respuestas se manejan mediante DTOs para desacoplar el modelo de
    dominio de la API.
-   La documentación interactiva está integrada automáticamente mediante
    Swagger.

------------------------------------------------------------------------

## Autor

Joaquin Aragon - Prueba Técnica Backend
