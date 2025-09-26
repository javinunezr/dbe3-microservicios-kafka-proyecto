# Proyecto Microservicios DBE3 - Semana 6

## 📋 Descripción

Este proyecto implementa una arquitectura de microservicios completa usando Spring Cloud, que incluye:

- **Config Server**: Servidor de configuración centralizada
- **Eureka Server**: Servidor de descubrimiento de servicios
- **API Gateway**: Puerta de enlace de la API
- **Backend Service**: Servicio backend principal con Oracle Cloud Database
- **Kafka Producer**: Servicio de mensajería con Apache Kafka

## 🏗️ Arquitectura

### Diagrama de Microservicios
```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Config Server │    │  Eureka Server  │    │   API Gateway   │
│     Port 8888   │    │     Port 8761   │    │     Port 8082   │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                │
                                ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ Backend Service │    │ Kafka Producer  │    │   Kafka Cluster │
│     Port 8081   │    │     Port 8080   │    │  Ports 29092,   │
│                 │    │                 │    │        39092    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Diagrama de Flujo de Eventos/Mensajes Kafka
```
    [Cliente/API]
          │
          │ HTTP POST /api/send
          ▼
┌─────────────────────┐
│  Kafka Producer     │
│  (Puerto 8080)      │
│                     │
│ ProducerController  │
│ KafkaProducerService│
└─────────────────────┘
          │
          │ Publica mensaje
          ▼
┌─────────────────────┐
│   Apache Kafka      │
│                     │
│ Topic:              │
│ "ejemplo-backend3"  │
│                     │
│ Brokers:            │
│ - localhost:29092   │
│ - localhost:39092   │
└─────────────────────┘
          │
          │ Mensaje almacenado
          ▼
┌─────────────────────┐
│   Kafka UI          │
│  (Puerto 8090)      │
│                     │
│ - Visualización     │
│ - Monitoreo         │
│ - Gestión Topics    │
└─────────────────────┘

Tipos de Eventos/Mensajes:
├── Mensajes de texto simple
├── Eventos de notificación
├── Logs de aplicación
└── Mensajes de prueba/testing
```

## 🛠️ Tecnologías Utilizadas

- **Java 21+**
- **Spring Boot 3.3.1**
- **Spring Cloud**
- **Maven**
- **Apache Kafka 7.4.0**
- **Oracle Cloud Database**
- **Docker & Docker Compose**
- **Kafka UI**

## 🚀 Instalación y Ejecución

### Prerrequisitos
- Java 21 o superior
- Maven 3.6+
- Docker y Docker Compose

### 1. Clonar el repositorio
```bash
git clone <tu-repositorio>
cd proyecto_semana6
```

### 2. Levantar Kafka con Docker Compose
```bash
cd kafka-producer-main/kafka-producer
docker-compose up -d
```

### 3. Ejecutar los servicios en orden

#### 3.1 Config Server
```bash
cd dbe3_exp3_s1_config-server-main
./mvnw spring-boot:run
```

#### 3.2 Eureka Server
```bash
cd dbe3_exp3_s1_eureka-server-main
./mvnw spring-boot:run
```

#### 3.3 Backend Service
```bash
cd dbe3_exp3_s1_backend-main
./mvnw spring-boot:run
```

#### 3.4 API Gateway
```bash
cd dbe3_exp3_s1_api-getaway-main
./mvnw spring-boot:run
```

#### 3.5 Kafka Producer
```bash
cd kafka-producer-main/kafka-producer
./mvnw spring-boot:run
```

## 📡 Endpoints Disponibles

### Backend Service (Puerto 8081)
- `GET /backend/api/annual-account` - Obtener cuentas anuales
- `GET /backend/api/interest` - Obtener intereses
- `GET /backend/api/transaction` - Obtener transacciones

### API Gateway (Puerto 8082)
- `GET /api/accounts` - Cuentas anuales (via gateway)
- `GET /api/interests` - Intereses (via gateway)
- `GET /api/transactions` - Transacciones (via gateway)

### Kafka Producer (Puerto 8080)
- `POST /api/send` - Enviar mensaje a Kafka
  ```bash
  curl -X POST "http://localhost:8080/api/send" \
       -d "message=Tu mensaje aquí" \
       -H "Content-Type: application/x-www-form-urlencoded"
  ```

### Servicios de Infraestructura
- **Eureka Dashboard**: http://localhost:8761
- **Kafka UI**: http://localhost:8090

## 🔧 Configuración

### Base de Datos Oracle Cloud
El proyecto está configurado para conectarse a Oracle Cloud Database. Las credenciales se encuentran en los archivos de configuración correspondientes.

### Kafka Configuration
- **Brokers**: localhost:29092, localhost:39092
- **Zookeepers**: localhost:22181, localhost:32181
- **Topic por defecto**: "ejemplo-backend3"

## 🧪 Testing

### Probar Kafka Producer
```bash
# Enviar mensaje
curl -X POST "http://localhost:8080/api/send" \
     -d "message=Hola Kafka!" \
     -H "Content-Type: application/x-www-form-urlencoded"
```

### Probar API Gateway
```bash
# Obtener transacciones via gateway
curl -X GET http://localhost:8082/api/transactions
```

## 📦 Estructura del Proyecto

```
proyecto_semana6/
├── dbe3_exp3_s1_config-server-main/     # Servidor de configuración
├── dbe3_exp3_s1_eureka-server-main/     # Servidor de descubrimiento
├── dbe3_exp3_s1_api-getaway-main/       # API Gateway
├── dbe3_exp3_s1_backend-main/           # Servicio Backend
├── kafka-producer-main/                 # Productor Kafka
│   ├── kafka-producer/                  # Aplicación Spring Boot
│   └── docker-compose.yml              # Configuración Kafka
├── .gitignore                           # Archivos ignorados por Git
└── README.md                            # Este archivo
```

## 🐳 Docker Services

El archivo `docker-compose.yml` incluye:
- **Zookeeper** (2 instancias para alta disponibilidad)
- **Kafka** (2 brokers)
- **Kafka UI** (Interfaz web para gestión)


**Desarrollo Backend III - Experiencia 3**

Proyecto académico - Semana 7
