
# CONSUMO DE API COVIDDATA

Este proyecto en Java con Spring Boot permite consumir una API externa que proporciona estadísticas sobre COVID-19 por región, provincia y fecha. Los datos se procesan y se almacenan automáticamente en una base de datos MySQL.




##  TECNOLOGIAS USADAS

- Java 17

- Spring Boot

- Spring Data JPA

- RestTemplate

- MySQL / MariaDB

- Maven

- NetBeans (IDE recomendado)

## Verificar la instalación:

com.myproject.coviddata

- ├── CoviddataApplication.java   // Clase principal con  @EnableScheduling
- ├── model/                   // Entidades (tablas): Region, Province, Report
- ├── repository/             // Repositorios JPA
- ├── service/       // Lógica para consumir API y guardar datos
- ├── controller/      // Endpoints REST para ejecutar manualmente
- └── config/          // Hilo automático que ejecuta los servicios

## 📅 Ejecución automática (Hilo)

- Un hilo automático configurado con @Scheduled ejecuta la carga de datos 15 segundos después de iniciar la aplicación:

  Carga regiones

  Carga provincias de Guatemala (GTM)

  Carga reportes por fecha y país (ej. 2022-04-16)

  Los datos se insertan en las tablas correspondientes.


## 🔍Endpoints disponibles (manuales)
- GET /regions/load → Carga regiones manualmente

- GET /provinces/load/GTM → Carga provincias para Guatemala

- GET /reports/load?iso=GTM&date=2022-04-16 → Carga reportes por país y fecha

## 📊 Base de datos

 Nombre sugerido: covid_data

 Tablas creadas automáticamente:

 region

 provinces

reports

 Configuración en application.properties:


## 🌟 Autores y colaboradores

 - Luis Hernandez : primer commit, creacion de API Key, y creacion de paquete modelos para las entidades y campos. Configuracion del Pom.xml y application.properties

 - Melquisedec Ordoñez: creacion de hilo y configuracion de consulta desde local host

 - Eddin Nij: Agregada la conexion a la base de datos y consumo de la appi Eddin Nij 19988






