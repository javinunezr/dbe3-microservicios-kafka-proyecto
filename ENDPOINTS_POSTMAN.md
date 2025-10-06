# 📋 Guía Completa de Endpoints para Postman

> ⚠️ **NOTA IMPORTANTE SOBRE ENDPOINTS DE FALLBACK:**
> Los endpoints `/fallback/*` (17, 18, 19) **NO se llaman directamente**.
> Son rutas internas del Gateway que se activan automáticamente cuando el backend falla.
> Si los pruebas directamente obtendrás un error 503, **esto es normal**.
> 
> **Para probar el fallback correctamente:**
> 1. Detén el backend: `docker compose -f docker-compose.microservices.yml stop backend-service`
> 2. Llama al endpoint normal: `GET http://localhost:8082/api/transactions` (10-15 veces)
> 3. El fallback se activará automáticamente

## 🎯 Resumen Rápido

| Servicio | Puerto | URL Base |
|----------|--------|----------|
| API Gateway | 8082 | http://localhost:8082 |
| Backend Service | 8181 | http://localhost:8181 |
| Kafka Producer | 8080 | http://localhost:8080 |
| Config Server | 8888 | http://localhost:8888 |
| Eureka Server | 8761 | http://localhost:8761 |

---

## 🚪 API GATEWAY (Puerto 8082) - **USAR ESTOS ENDPOINTS**

### ✅ 1. Obtener Transacciones
- **Método:** `GET`
- **URL:** `http://localhost:8082/api/transactions`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:** Lista de transacciones en formato JSON

**Ejemplo en Postman:**
```
GET http://localhost:8082/api/transactions
```

---

### ✅ 2. Obtener Cuentas Anuales
- **Método:** `GET`
- **URL:** `http://localhost:8082/api/accounts`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:** Lista de cuentas anuales en formato JSON

**Ejemplo en Postman:**
```
GET http://localhost:8082/api/accounts
```

---

### ✅ 3. Obtener Intereses
- **Método:** `GET`
- **URL:** `http://localhost:8082/api/interests`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:** Lista de intereses en formato JSON

**Ejemplo en Postman:**
```
GET http://localhost:8082/api/interests
```

---

### ✅ 4. Health Check del Gateway
- **Método:** `GET`
- **URL:** `http://localhost:8082/actuator/health`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:**
```json
{
  "status": "UP",
  "components": {
    "discoveryComposite": {
      "status": "UP"
    }
  }
}
```

---

### ✅ 5. Estado de Circuit Breakers
- **Método:** `GET`
- **URL:** `http://localhost:8082/actuator/circuitbreakers`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:**
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

---

### ✅ 6. Eventos del Circuit Breaker
- **Método:** `GET`
- **URL:** `http://localhost:8082/actuator/circuitbreakerevents`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:** Historial de eventos del Circuit Breaker

---

### ✅ 7. Información del Gateway
- **Método:** `GET`
- **URL:** `http://localhost:8082/actuator/info`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:** Información de la aplicación

---

## 💼 BACKEND SERVICE (Puerto 8181) - Acceso Directo

### ✅ 8. Obtener Transacciones (Directo)
- **Método:** `GET`
- **URL:** `http://localhost:8181/backend/api/transaction`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:** Lista de transacciones

**Ejemplo en Postman:**
```
GET http://localhost:8181/backend/api/transaction
```

---

### ✅ 9. Obtener Cuentas Anuales (Directo)
- **Método:** `GET`
- **URL:** `http://localhost:8181/backend/api/annual-account`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:** Lista de cuentas anuales

**Ejemplo en Postman:**
```
GET http://localhost:8181/backend/api/annual-account
```

---

### ✅ 10. Obtener Intereses (Directo)
- **Método:** `GET`
- **URL:** `http://localhost:8181/backend/api/interest`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:** Lista de intereses

**Ejemplo en Postman:**
```
GET http://localhost:8181/backend/api/interest
```

---

## 📨 KAFKA PRODUCER (Puerto 8080)

### ✅ 11. Enviar Mensaje a Kafka
- **Método:** `POST`
- **URL:** `http://localhost:8080/api/send`
- **Headers:** 
  - `Content-Type: application/x-www-form-urlencoded`
- **Body:** (x-www-form-urlencoded)
  - Key: `message`
  - Value: `Tu mensaje aquí`
- **Respuesta esperada:** Confirmación del envío

**Ejemplo en Postman:**
```
POST http://localhost:8080/api/send
Content-Type: application/x-www-form-urlencoded

Body (x-www-form-urlencoded):
message=Hola desde Postman!
```

**Pasos en Postman:**
1. Selecciona método: `POST`
2. URL: `http://localhost:8080/api/send`
3. Ve a la pestaña `Body`
4. Selecciona `x-www-form-urlencoded`
5. Agrega key: `message`, value: `Hola desde Postman!`
6. Click en `Send`

---

## 🔍 EUREKA SERVER (Puerto 8761)

### ✅ 12. Dashboard de Eureka
- **Método:** `GET`
- **URL:** `http://localhost:8761`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:** Página HTML del dashboard

**Nota:** Mejor abrir en navegador

---

### ✅ 13. Lista de Aplicaciones Registradas (JSON)
- **Método:** `GET`
- **URL:** `http://localhost:8761/eureka/apps`
- **Headers:** 
  - `Accept: application/json`
- **Body:** Ninguno
- **Respuesta esperada:** JSON con todas las aplicaciones registradas

**Ejemplo en Postman:**
```
GET http://localhost:8761/eureka/apps
Accept: application/json
```

---

### ✅ 14. Información de un Servicio Específico
- **Método:** `GET`
- **URL:** `http://localhost:8761/eureka/apps/BACKEND-SERVICE`
- **Headers:** 
  - `Accept: application/json`
- **Body:** Ninguno
- **Respuesta esperada:** Información del backend-service

**Ejemplo en Postman:**
```
GET http://localhost:8761/eureka/apps/BACKEND-SERVICE
Accept: application/json
```

---

### ✅ 15. Información del API Gateway en Eureka
- **Método:** `GET`
- **URL:** `http://localhost:8761/eureka/apps/API-GATEWAY`
- **Headers:** 
  - `Accept: application/json`
- **Body:** Ninguno
- **Respuesta esperada:** Información del api-gateway

---

## 🔧 CONFIG SERVER (Puerto 8888)

### ✅ 16. Health Check del Config Server
- **Método:** `GET`
- **URL:** `http://localhost:8888/actuator/health`
- **Headers:** Ninguno requerido
- **Body:** Ninguno
- **Respuesta esperada:**
```json
{
  "status": "UP"
}
```

---

## 🎭 ENDPOINTS DE FALLBACK (⚠️ NO LLAMAR DIRECTAMENTE)

> **IMPORTANTE:** Estos endpoints son **rutas internas del Gateway** que se activan automáticamente.
> **NO funcionan** si los llamas directamente con `GET http://localhost:8082/fallback/...`
> 
> Para probar el fallback, debes:
> 1. Detener el backend: `docker compose -f docker-compose.microservices.yml stop backend-service`
> 2. Llamar al endpoint normal: `GET http://localhost:8082/api/transactions` (10-15 veces)
> 3. El Gateway automáticamente te redirigirá al fallback

### 🔴 17. Fallback de Transacciones
- **URL interna:** `http://localhost:8082/fallback/transactions`
- **Cómo probar:** Llama a `GET http://localhost:8082/api/transactions` con el backend detenido
- **Activación:** Automática cuando backend-service no responde o Circuit Breaker está OPEN
- **Respuesta esperada:**
```json
{
  "servicio": "transactions",
  "mensaje": "El servicio de transacciones está caído. Inténtalo nuevamente más tarde.",
  "error": "Falla en el backend",
  "fecha y hora": "2025-10-06T17:53:25.601125176",
  "fallback": true
}
```

---

### 🔴 18. Fallback de Cuentas
- **URL interna:** `http://localhost:8082/fallback/accounts`
- **Cómo probar:** Llama a `GET http://localhost:8082/api/accounts` con el backend detenido
- **Activación:** Automática cuando backend-service no responde

---

### 🔴 19. Fallback de Intereses
- **URL interna:** `http://localhost:8082/fallback/interests`
- **Cómo probar:** Llama a `GET http://localhost:8082/api/interests` con el backend detenido
- **Activación:** Automática cuando backend-service no responde

---

## 🌐 INTERFACES WEB

### ✅ 20. Kafka UI
- **URL:** `http://localhost:8090`
- **Tipo:** Interfaz Web
- **Descripción:** Interfaz para visualizar topics, mensajes y brokers de Kafka
- **Uso:** Abrir en navegador

---

## 📝 COLECCIÓN PARA POSTMAN

### Cómo importar en Postman:

1. Abre Postman
2. Click en "Import"
3. Crea una nueva colección llamada "Microservicios DBE3"
4. Agrega las siguientes carpetas:
   - **API Gateway** (endpoints 1-7)
   - **Backend Direct** (endpoints 8-10)
   - **Kafka Producer** (endpoint 11)
   - **Eureka Server** (endpoints 12-15)
   - **Config Server** (endpoint 16)
   - **Monitoring** (endpoints de actuator)

---

## 🧪 CASOS DE PRUEBA RECOMENDADOS

### Test 1: Flujo Normal ✅
1. Probar `GET http://localhost:8082/api/transactions`
2. Probar `GET http://localhost:8082/api/accounts`
3. Probar `GET http://localhost:8082/api/interests`
4. Verificar Circuit Breaker: `GET http://localhost:8082/actuator/circuitbreakers`

### Test 2: Circuit Breaker y Fallback 🔴 (MUY IMPORTANTE)

**Instrucciones paso a paso:**

1. **Verificar estado inicial (CLOSED):**
   ```bash
   GET http://localhost:8082/actuator/circuitbreakers
   # Debe mostrar: "state": "CLOSED"
   ```

2. **Detener el backend-service:**
   ```bash
   docker compose -f docker-compose.microservices.yml stop backend-service
   ```

3. **En Postman, hacer 10-15 peticiones al endpoint NORMAL (NO al fallback):**
   ```bash
   GET http://localhost:8082/api/transactions
   ```
   - Click en "Send" 10-15 veces seguidas
   - ⚠️ **NO uses** `http://localhost:8082/fallback/transactions`
   - Las primeras peticiones fallarán con timeout
   - Después de 5-10 peticiones, verás el mensaje de fallback automáticamente

4. **Verificar que el fallback se activó:**
   - La respuesta debe incluir: `"fallback": true`
   - Mensaje: "El servicio de transacciones está caído..."

5. **Verificar estado del Circuit Breaker (OPEN):**
   ```bash
   GET http://localhost:8082/actuator/circuitbreakers
   # Debe mostrar: "state": "OPEN" o "HALF_OPEN"
   # failureRate debe ser > 50%
   ```

6. **Reiniciar el backend-service:**
   ```bash
   docker compose -f docker-compose.microservices.yml start backend-service
   ```

7. **Esperar 10-15 segundos y probar de nuevo:**
   ```bash
   GET http://localhost:8082/api/transactions
   # Debe volver a mostrar datos reales (no fallback)
   ```

8. **Verificar recuperación (CLOSED):**
   ```bash
   GET http://localhost:8082/actuator/circuitbreakers
   # Debe volver a: "state": "CLOSED"
   ```

### Test 3: Kafka 📨
1. Enviar mensaje: `POST http://localhost:8080/api/send` con body `message=Test desde Postman`
2. Abrir navegador: `http://localhost:8090`
3. Ver el mensaje en el topic "ejemplo-backend3"

### Test 4: Comparación Gateway vs Directo ⚡
1. Probar via Gateway: `GET http://localhost:8082/api/transactions`
2. Probar directo: `GET http://localhost:8181/backend/api/transaction`
3. Comparar tiempos de respuesta

---

## 📊 RESPUESTAS ESPERADAS (Ejemplos)

### Transacciones:
```json
[
  {
    "id": 451,
    "transactionDate": "2024-07-02",
    "amount": 700,
    "type": "invalid"
  },
  {
    "id": 452,
    "transactionDate": "2024-05-31",
    "amount": 700,
    "type": "credito"
  }
]
```

### Cuentas:
```json
[
  {
    "id": 836,
    "accountId": "103",
    "date": "03/10/2024",
    "transactionType": "compra",
    "amount": 1500,
    "transactionDescription": "Ingreso navideño"
  }
]
```

### Intereses:
```json
[
  {
    "accountId": "137",
    "clientName": "Bob Johnson",
    "balance": 7000,
    "clientAge": null,
    "interestType": "prestamo"
  }
]
```

---

## ⚙️ VARIABLES DE ENTORNO SUGERIDAS PARA POSTMAN

Crea un Environment en Postman con estas variables:

| Variable | Valor |
|----------|-------|
| `gateway_url` | `http://localhost:8082` |
| `backend_url` | `http://localhost:8181` |
| `kafka_url` | `http://localhost:8080` |
| `eureka_url` | `http://localhost:8761` |
| `config_url` | `http://localhost:8888` |

Luego usa: `{{gateway_url}}/api/transactions`

---

## 🔥 ENDPOINTS PRIORITARIOS PARA DEMOSTRACIÓN

Si tienes poco tiempo, prueba estos **5 endpoints esenciales**:

1. ✅ `GET {{gateway_url}}/api/transactions` - Endpoint principal
2. ✅ `GET {{gateway_url}}/actuator/circuitbreakers` - Estado de resiliencia
3. ✅ `POST {{kafka_url}}/api/send` - Mensajería Kafka
4. ✅ `GET {{eureka_url}}/eureka/apps` - Service Discovery
5. ✅ `GET {{gateway_url}}/actuator/health` - Health Check

---

## 📞 TROUBLESHOOTING

### Error: Connection Refused
- **Solución:** Verificar que los contenedores estén corriendo
- **Comando:** `docker ps | grep proyecto_semana6`

### Error: 503 Service Unavailable
- **Causa:** Backend service caído o Circuit Breaker abierto
- **Solución:** Verificar logs: `docker compose -f docker-compose.microservices.yml logs backend-service`

### Error: Timeout
- **Causa:** Base de datos Oracle Cloud lenta
- **Nota:** Es normal en la primera petición, reintentar

---

**Última actualización:** 6 de octubre de 2025
**Proyecto:** Desarrollo Backend III - Semana 8
