# Desarrollo Backend 3 | Exp3-S1


## Objetivo del proyecto

En este proyecto de la semana 1 de la experiencia 3 busca implementar las herramientas para el desarrollo de proyectos
en la nube con Spring Cloud en microservicios


Estos son los siguientes repositorios que componen todo el proyecto.


## 🧩 Proyectos
[Config Server](https://github.com/nisiara/dbe3_exp3_s1_config-server.git)

[Eureka Server](https://github.com/nisiara/dbe3_exp3_s1_eureka-server.git)

[API Getaway](https://github.com/nisiara/dbe3_exp3_s1_api-getaway.git)

## 🛠️ Requisitos
- Java 21
- Maven 4.0
- Dependencias
  - Spring Web
  - Spring Data JPA
  - Oracle Driver
  - Spring Boot Dev Tools
  - Eureka Client
  - Lomkok


## 💾 Base de datos

#### La base de datos está alojada en Oracle Cloud.


## 📡 Backend Enddpoints

#### Endpoint para Cuentas Anuales (Retorna solo el id de la cuenta)
```bash
curl -X GET http://localhost:8081/backend/api/annual-account
```

#### Endpoint para Intereses
```bash
curl -X GET http://localhost:8081/backend/api/interest
```

#### Endpoint Transacciones
```bash
curl -X GET http://localhost:8081/backend/api/transaction
```

## 🔀 API Getaway Enddpoints

#### Endpoint para Cuentas Anuales
```bash
curl -X GET http://localhost:8082/api/accounts
```

#### Endpoint para Intereses
```bash
curl -X GET http://localhost:8082/api/interests
```

#### Endpoint Transacciones
```bash
curl -X GET http://localhost:8082/api/transactions
```

#### Endpoint Kafka
```bash
curl -X GET http://localhost:8080/api/send

En este apartado se utiliza como parámetro:
key: message
Value: "mensaje que se quiera enviar"
```


## 🔗 Link
[![Github](https://img.shields.io/badge/github-000000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/nisiara/dbe3_exp3_s1_backend.git)