# Proyecto Microservicios DBE3 - Semana 8

## 📋 Descripción

Este proyecto implementa una arquitectura de microservicios completa usando Spring Cloud, que incluye:

- **Config Server**: Servidor de configuración centralizada
- **Eureka Server**: Servidor de descubrimiento de servicios
- **API Gateway**: Puerta de enlace de la API con Resilience4j (Circuit Breaker, Retry, Time Limiter)
- **Backend Service**: Servicio backend principal con Oracle Cloud Database
- **Kafka Producer**: Servicio de mensajería con Apache Kafka

## 🆕 Características Implementadas

### ✅ Resilience4j - Patrones de Resiliencia
- **Circuit Breaker**: Protección contra fallos en cascada
  - Ventana deslizante: 10 llamadas
  - Umbral de fallo: 50%
  - Tiempo en estado abierto: 10 segundos
  
- **Retry**: Reintentos automáticos con backoff exponencial
  - Máximo 3 intentos
  - Tiempo de espera: 1 segundo (con multiplicador x2)
  
- **Time Limiter**: Control de timeouts
  - Timeout: 5 segundos por solicitud

### ✅ Docker Compose - Orquestación de Contenedores
- Despliegue completo de microservicios con dependencias controladas
- Healthchecks configurados para inicio ordenado
- Volúmenes montados para Oracle Wallet y configuraciones
- Variables de entorno para configuración Docker

### ✅ Spring Boot Actuator
- Monitoreo en tiempo real de Circuit Breakers
- Endpoints de health con información detallada
- Métricas de resiliencia disponibles

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
- **Spring Boot 3.5.6**
- **Spring Cloud 2025.0.0**
- **Spring Cloud Gateway** con filtros reactivos
- **Resilience4j** (Circuit Breaker, Retry, Time Limiter)
- **Spring Boot Actuator** para monitoreo
- **Netflix Eureka** para Service Discovery
- **Spring Cloud Config** para configuración centralizada
- **Maven**
- **Apache Kafka 7.4.0**
- **Oracle Cloud Database** con Wallet authentication
- **Docker & Docker Compose**
- **Kafka UI**

## 🚀 Instalación y Ejecución

### Prerrequisitos
- Java 21 o superior
- Maven 3.6+
- Docker y Docker Compose

### 1. Clonar el repositorio
```bash
git clone
```

### 2. Opción A: Ejecutar con Docker Compose (Recomendado) 🐳

Esta opción levanta todos los microservicios con un solo comando:

```bash
# Construir las imágenes Docker
docker compose -f docker-compose.microservices.yml build

# Levantar todos los servicios
docker compose -f docker-compose.microservices.yml up -d

# Ver logs de todos los servicios
docker compose -f docker-compose.microservices.yml logs -f

# Ver logs de un servicio específico
docker compose -f docker-compose.microservices.yml logs -f backend-service

# Detener todos los servicios
docker compose -f docker-compose.microservices.yml down
```

**Servicios incluidos en Docker Compose:**
- Config Server (8888) - Con healthcheck
- Eureka Server (8761)
- API Gateway (8082) - Con Resilience4j
- Backend Service (8181) - Con Oracle Cloud Database

### 3. Opción B: Ejecutar servicios individualmente con Maven

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

### 4. Levantar Kafka (Independiente)
```bash
cd kafka-producer-main/kafka-producer
docker-compose up -d
```

#### 4.1 Kafka Producer
```bash
cd kafka-producer-main/kafka-producer
./mvnw spring-boot:run
```

## 📡 Endpoints Disponibles

> 💡 **Nota:** Se han creado dos archivos con la documentación completa de endpoints:
> - **`ENDPOINTS_POSTMAN.md`** - Guía detallada de todos los endpoints
> - **`Postman_Collection_Microservicios_DBE3.json`** - Colección para importar en Postman
> 
> ⚠️ **IMPORTANTE - Endpoints de Fallback:**
> Los endpoints `/fallback/*` **NO se llaman directamente**. Son rutas internas que se activan
> automáticamente cuando el backend falla. Para probar el Circuit Breaker, detén el backend
> y llama a los endpoints normales (ej: `/api/transactions`), no a los de fallback.

### 🚪 API Gateway (Puerto 8082) - **RECOMENDADO**

#### Endpoints de Negocio
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/transactions` | Obtener transacciones (con Circuit Breaker) |
| GET | `/api/accounts` | Obtener cuentas anuales (con Circuit Breaker) |
| GET | `/api/interests` | Obtener intereses (con Circuit Breaker) |

#### Endpoints de Monitoreo (Actuator)
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/actuator/health` | Estado de salud del Gateway |
| GET | `/actuator/circuitbreakers` | Estado de los Circuit Breakers |
| GET | `/actuator/circuitbreakerevents` | Historial de eventos del Circuit Breaker |
| GET | `/actuator/info` | Información de la aplicación |

**Ejemplos con curl:**
```bash
# Transacciones
curl http://localhost:8082/api/transactions

# Estado del Circuit Breaker
curl http://localhost:8082/actuator/circuitbreakers

# Health Check
curl http://localhost:8082/actuator/health
```

---

### 💼 Backend Service (Puerto 8181) - Acceso Directo

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/backend/api/transaction` | Obtener transacciones (sin Circuit Breaker) |
| GET | `/backend/api/annual-account` | Obtener cuentas anuales (sin Circuit Breaker) |
| GET | `/backend/api/interest` | Obtener intereses (sin Circuit Breaker) |

**Ejemplos con curl:**
```bash
# Transacciones directamente al backend
curl http://localhost:8181/backend/api/transaction

# Cuentas anuales
curl http://localhost:8181/backend/api/annual-account

# Intereses
curl http://localhost:8181/backend/api/interest
```

---

### 📨 Kafka Producer (Puerto 8080)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/send` | Enviar mensaje al topic de Kafka |

**Ejemplo con curl:**
```bash
curl -X POST "http://localhost:8080/api/send" \
     -d "message=Hola desde curl!" \
     -H "Content-Type: application/x-www-form-urlencoded"
```

**Ejemplo en Postman:**
- Método: `POST`
- URL: `http://localhost:8080/api/send`
- Body: `x-www-form-urlencoded`
- Key: `message`, Value: `Tu mensaje aquí`

---

### 🔍 Eureka Server (Puerto 8761)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/` | Dashboard HTML de Eureka |
| GET | `/eureka/apps` | Lista de aplicaciones registradas (JSON) |
| GET | `/eureka/apps/BACKEND-SERVICE` | Info del Backend Service |
| GET | `/eureka/apps/API-GATEWAY` | Info del API Gateway |

**Ejemplos con curl:**
```bash
# Ver todas las aplicaciones registradas (JSON)
curl -H "Accept: application/json" http://localhost:8761/eureka/apps

# Ver info del backend-service
curl -H "Accept: application/json" http://localhost:8761/eureka/apps/BACKEND-SERVICE
```

---

### 🔧 Config Server (Puerto 8888)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/actuator/health` | Estado de salud del Config Server |

---

### 🌐 Interfaces Web

| Servicio | URL | Descripción |
|----------|-----|-------------|
| **Eureka Dashboard** | http://localhost:8761 | Visualización de servicios registrados |
| **Kafka UI** | http://localhost:8090 | Gestión y monitoreo de Kafka |

---

### 📥 Importar en Postman

1. Abre Postman
2. Click en **"Import"**
3. Selecciona el archivo: **`Postman_Collection_Microservicios_DBE3.json`**
4. La colección incluye todos los 19 endpoints configurados
5. Variables de entorno ya configuradas

---

### 🎯 Endpoints Prioritarios para Demostración

Si tienes poco tiempo, usa estos **5 endpoints esenciales**:

1. ✅ `GET http://localhost:8082/api/transactions` - Endpoint principal de negocio
2. ✅ `GET http://localhost:8082/actuator/circuitbreakers` - Estado de resiliencia
3. ✅ `POST http://localhost:8080/api/send` - Mensajería Kafka
4. ✅ `GET http://localhost:8761/eureka/apps` - Service Discovery
5. ✅ `GET http://localhost:8082/actuator/health` - Health Check

## 🔧 Configuración

### Base de Datos Oracle Cloud
El proyecto está configurado para conectarse a Oracle Cloud Database. Las credenciales se encuentran en los archivos de configuración correspondientes.

### Kafka Configuration
- **Brokers**: localhost:29092, localhost:39092
- **Zookeepers**: localhost:22181, localhost:32181
- **Topic por defecto**: "ejemplo-backend3"

## 📊 Monitoreo con Spring Boot Actuator

### Endpoints de Monitoreo (API Gateway - Puerto 8082)

#### Health Check
```bash
curl http://localhost:8082/actuator/health
```
Respuesta esperada:
```json
{
  "status": "UP",
  "components": {
    "discoveryComposite": {
      "status": "UP",
      "components": {
        "discoveryClient": {
          "status": "UP",
          "details": {
            "services": ["backend-service", "api-gateway"]
          }
        }
      }
    }
  }
}
```

#### Estado de Circuit Breakers
```bash
curl http://localhost:8082/actuator/circuitbreakers
```
Respuesta esperada:
```json
{
  "circuitBreakers": {
    "backend-circuit-breaker": {
      "state": "CLOSED",
      "failureRate": "-1.0%",
      "bufferedCalls": 0,
      "slowCallRate": "-1.0%"
    }
  }
}
```

**Estados del Circuit Breaker:**
- **CLOSED** (🟢): Funcionamiento normal, las peticiones pasan al backend
- **OPEN** (🔴): Circuito abierto, peticiones van directo al fallback
- **HALF_OPEN** (🟡): Estado de prueba, permite algunas peticiones para verificar recuperación

#### Eventos del Circuit Breaker
```bash
curl http://localhost:8082/actuator/circuitbreakerevents
```

## 🧪 Testing y Pruebas

### 1. Probar funcionamiento normal
```bash
# Verificar que el backend responde
curl http://localhost:8082/api/transactions
curl http://localhost:8082/api/accounts
curl http://localhost:8082/api/interests
```

### 2. Probar Circuit Breaker y Fallback

**Paso 1:** Detener el backend-service para simular fallo
```bash
docker compose -f docker-compose.microservices.yml stop backend-service
# O si ejecutas con Maven: Ctrl+C en la terminal del backend
```

**Paso 2:** Hacer varias peticiones (mínimo 10 para llenar la ventana deslizante)
```bash
for i in {1..15}; do
  echo "Request $i:"
  curl http://localhost:8082/api/transactions
  echo -e "\n---"
done
```

**Respuesta esperada (Fallback):**
```json
{
  "error": "Servicio no disponible",
  "mensaje": "El servicio de transacciones está temporalmente fuera de servicio. Por favor, intente más tarde.",
  "fecha y hora": "2024-01-15T10:30:45",
  "servicio": "transactions",
  "fallback": true
}
```

**Paso 3:** Verificar que el Circuit Breaker se abrió
```bash
curl http://localhost:8082/actuator/circuitbreakers
```
Esperado: `"state": "OPEN"` con `failureRate` mayor a 50%

**Paso 4:** Reiniciar el backend-service
```bash
docker compose -f docker-compose.microservices.yml start backend-service
```

**Paso 5:** Esperar 10 segundos (waitDurationInOpenState) y el Circuit Breaker pasará a HALF_OPEN, luego a CLOSED si las peticiones tienen éxito.

### 3. Probar Retry Mechanism

El retry ocurre automáticamente en fallos transitorios. Puedes ver los logs del API Gateway:
```bash
docker compose -f docker-compose.microservices.yml logs -f api-gateway
```

Deberías ver mensajes indicando reintentos antes de activar el fallback.

### 4. Probar Time Limiter (Timeout)

Si el backend tarda más de 5 segundos, se activará el timeout:
```bash
# Esto requiere modificar el backend para simular lentitud
# Por ejemplo, agregando Thread.sleep(6000) en un endpoint
```

### 5. Probar Kafka Producer
```bash
# Enviar mensaje
curl -X POST "http://localhost:8080/api/send" \
     -d "message=Hola Kafka desde el test!" \
     -H "Content-Type: application/x-www-form-urlencoded"

# Ver el mensaje en Kafka UI
# Abrir navegador: http://localhost:8090
```

## 📦 Estructura del Proyecto

```
proyecto_semana6/
├── dbe3_exp3_s1_config-server-main/     # 🔧 Servidor de Configuración Centralizada
│   ├── src/main/resources/config/       # Configuraciones para todos los servicios
│   └── pom.xml                          # Spring Cloud Config Server
│
├── dbe3_exp3_s1_eureka-server-main/     # 🔍 Service Discovery
│   ├── src/main/resources/
│   │   └── application.yml              # Puerto 8761
│   └── pom.xml                          # Netflix Eureka Server
│
├── dbe3_exp3_s1_api-getaway-main/       # 🚪 API Gateway con Resilience4j
│   ├── src/main/java/
│   │   └── controller/
│   │       └── FallbackController.java  # Endpoints de fallback
│   ├── src/main/resources/
│   │   └── application.yml              # Configuración Circuit Breaker, Retry, Time Limiter
│   ├── pom.xml                          # Spring Cloud Gateway + Resilience4j
│   └── Dockerfile                       # Imagen Docker del Gateway
│
├── dbe3_exp3_s1_backend-main/           # 💼 Servicio Backend Principal
│   ├── src/main/java/com/bancoxyz/backend/
│   │   ├── controller/                  # REST Controllers
│   │   ├── service/                     # Lógica de negocio
│   │   └── repository/                  # Acceso a Oracle DB
│   ├── Wallet_dbe3db/                   # Oracle Cloud Wallet
│   ├── pom.xml                          # Spring Boot + Oracle Driver
│   └── Dockerfile                       # Imagen Docker del Backend
│
├── kafka-producer-main/                 # 📨 Productor de Mensajes Kafka
│   ├── kafka-producer/
│   │   ├── src/main/java/
│   │   ├── pom.xml                      # Spring Kafka
│   │   └── docker-compose.yml           # Infraestructura Kafka
│   └── HELP.md
│
├── docker-compose.microservices.yml     # 🐳 Orquestación de Microservicios
│   # Incluye: config-server, eureka-server, api-gateway, backend-service
│
├── .gitignore                           # Archivos ignorados por Git
└── README.md                            # 📖 Esta documentación
```

## 🐳 Docker Services

### docker-compose.microservices.yml
Orquesta todos los microservicios Spring:
- **config-server** (8888) - Con healthcheck para sincronización
- **eureka-server** (8761) - Registro y descubrimiento
- **api-gateway** (8082) - Gateway con Resilience4j
- **backend-service** (8181) - Backend con Oracle DB

### kafka-producer/docker-compose.yml
Infraestructura de mensajería:
- **Zookeeper** (2 instancias: 22181, 32181) - Coordinación
- **Kafka Broker 1** (29092) - Mensajería
- **Kafka Broker 2** (39092) - Alta disponibilidad
- **Kafka UI** (8090) - Interfaz web de gestión


**Desarrollo Backend III - Experiencia 3**

Proyecto académico - Semana 7
