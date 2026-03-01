# 🎓 Plataforma Web de Gestión Académica

> **Proyecto Final — Construcción y Evolución del Software**  
> API REST desarrollada con **Spring Boot 3.5** para la gestión de estudiantes, docentes, cursos, matrículas y calificaciones, aplicando prácticas modernas de **DevOps**.

---

## 📋 Tabla de Contenidos

- [Descripción del Proyecto](#-descripción-del-proyecto)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [Ejecución con Docker](#-ejecución-con-docker)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Pruebas y Cobertura](#-pruebas-y-cobertura)
- [Pipeline CI/CD](#-pipeline-cicd)

---

## 📌 Descripción del Proyecto

Sistema backend de gestión académica que expone una **API REST** para administrar las entidades principales de un entorno educativo. El proyecto aplica un enfoque DevOps completo: automatización de builds con Maven, pruebas unitarias e integración con JUnit 5 y Mockito, contenedores Docker y pipeline de integración continua con GitHub Actions.

### Módulos del Sistema

| Módulo | Descripción |
|---|---|
| **Estudiantes** | Registro, consulta, actualización y eliminación de estudiantes (nombre, correo, carrera) |
| **Docentes** | Gestión del personal docente con nombre y especialidad |
| **Cursos** | Administración de cursos con créditos y asignación de docente |
| **Matrículas** | Inscripciones de estudiantes a cursos con fecha de registro |
| **Calificaciones** | Registro de notas vinculadas a matrículas |

---

## 🛠 Tecnologías Utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 17 LTS | Lenguaje principal |
| Spring Boot | 3.5.11 | Framework backend REST |
| Spring Data JPA | — | ORM / Acceso a base de datos con Hibernate |
| Spring Validation | — | Validación de datos de entrada |
| PostgreSQL | 15+ | Base de datos relacional |
| Apache Maven | 3.9.x | Build tool y gestión de dependencias |
| Lombok | — | Reducción de código repetitivo |
| Spring Boot DevTools | — | Recarga automática en desarrollo |
| JUnit 5 + Mockito | — | Pruebas unitarias e integración |
| JaCoCo | — | Cobertura de código |
| Docker | — | Contenedores de la aplicación |
| GitHub Actions | — | Pipeline CI/CD automatizado |

---

## ✅ Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- [Java JDK 17+](https://adoptium.net/)
- [Apache Maven 3.9+](https://maven.apache.org/download.cgi) *(o usar el Maven Wrapper incluido `./mvnw`)*
- [PostgreSQL 15+](https://www.postgresql.org/download/) con una base de datos llamada `gestionBilly`
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) *(solo para ejecución con contenedores)*
- [Git](https://git-scm.com/)

---

## 🚀 Instalación y Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/Billygg2/proyectFinal.git
cd proyectFinal
```

### 2. Configurar la base de datos

Crea la base de datos en PostgreSQL antes de ejecutar:

```sql
CREATE DATABASE "gestionBilly";
```

Verifica que el archivo `src/main/resources/application.properties` tenga tus credenciales:

```properties
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/gestionBilly
spring.datasource.username=postgres
spring.datasource.password=123

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

> ⚠️ Cambia `username` y `password` según tu configuración local si es diferente.

### 3. Compilar el proyecto

```bash
# Con Maven Wrapper (recomendado, no requiere Maven instalado)
./mvnw clean compile

# O con Maven instalado globalmente
mvn clean compile
```

### 4. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

Una vez iniciado, la aplicación estará disponible en:

| Recurso | URL |
|---|---|
| API Base | http://localhost:8080/api |
| Swagger UI | http://localhost:8080/swagger-ui.html |

---

## 🐳 Ejecución con Docker

### 1. Generar el JAR primero

```bash
./mvnw clean package -DskipTests
```

### 2. Construir la imagen Docker

```bash
docker build -t gestionacademica:latest .
```

### 3. Ejecutar el contenedor

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/gestionBilly \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=123 \
  gestionacademica:latest
```

> 💡 `host.docker.internal` permite que el contenedor se conecte a PostgreSQL instalado en tu máquina local.

### Con Docker Compose (app + PostgreSQL)

```bash
# Levantar todos los servicios
docker-compose up -d

# Ver logs de la aplicación
docker-compose logs -f app

# Detener los servicios
docker-compose down
```

---

## 📡 Endpoints de la API

> 📖 Documentación interactiva completa disponible en: **http://localhost:8080/swagger-ui.html**

### Estudiantes — `/api/estudiantes`

| Método | Endpoint | Descripción | Body requerido |
|---|---|---|---|
| `GET` | `/api/estudiantes` | Listar todos los estudiantes | — |
| `GET` | `/api/estudiantes/{id}` | Obtener estudiante por ID | — |
| `POST` | `/api/estudiantes` | Crear nuevo estudiante | `{ "nombre", "correo", "carrera" }` |
| `PUT` | `/api/estudiantes/{id}` | Actualizar datos del estudiante | `{ "nombre", "correo", "carrera" }` |
| `DELETE` | `/api/estudiantes/{id}` | Eliminar estudiante | — |

**Ejemplo de body:**
```json
{
  "nombre": "Juan Pérez",
  "correo": "juan@mail.com",
  "carrera": "Ingeniería de Sistemas"
}
```

### Docentes — `/api/docentes`

| Método | Endpoint | Descripción | Body requerido |
|---|---|---|---|
| `GET` | `/api/docentes` | Listar todos los docentes | — |
| `GET` | `/api/docentes/{id}` | Obtener docente por ID | — |
| `POST` | `/api/docentes` | Crear nuevo docente | `{ "nombre", "especialidad" }` |
| `PUT` | `/api/docentes/{id}` | Actualizar datos del docente | `{ "nombre", "especialidad" }` |
| `DELETE` | `/api/docentes/{id}` | Eliminar docente | — |

### Cursos — `/api/cursos`

| Método | Endpoint | Descripción | Body requerido |
|---|---|---|---|
| `GET` | `/api/cursos` | Listar todos los cursos | — |
| `GET` | `/api/cursos/{id}` | Obtener curso por ID | — |
| `POST` | `/api/cursos` | Crear nuevo curso | `{ "nombre", "creditos", "docente": { "id" } }` |
| `PUT` | `/api/cursos/{id}` | Actualizar curso | `{ "nombre", "creditos", "docente": { "id" } }` |
| `DELETE` | `/api/cursos/{id}` | Eliminar curso | — |

### Matrículas — `/api/matriculas`

| Método | Endpoint | Descripción | Body requerido |
|---|---|---|---|
| `GET` | `/api/matriculas` | Listar todas las matrículas | — |
| `GET` | `/api/matriculas/{id}` | Obtener matrícula por ID | — |
| `POST` | `/api/matriculas` | Registrar nueva matrícula | `{ "estudiante": { "id" }, "curso": { "id" }, "fecha": "2025-01-15" }` |
| `DELETE` | `/api/matriculas/{id}` | Eliminar matrícula | — |

### Calificaciones — `/api/calificaciones`

| Método | Endpoint | Descripción | Body requerido |
|---|---|---|---|
| `GET` | `/api/calificaciones` | Listar todas las calificaciones | — |
| `GET` | `/api/calificaciones/{id}` | Obtener calificación por ID | — |
| `POST` | `/api/calificaciones` | Registrar calificación | `{ "matricula": { "id" }, "nota": 8.5 }` |
| `PUT` | `/api/calificaciones/{id}` | Actualizar calificación | `{ "nota": 9.0 }` |
| `DELETE` | `/api/calificaciones/{id}` | Eliminar calificación | — |

---

## 🧪 Pruebas y Cobertura

### Ejecutar todas las pruebas

```bash
./mvnw test
```

### Ejecutar pruebas y generar reporte de cobertura (JaCoCo)

```bash
./mvnw clean verify
```

El reporte de cobertura se genera en:

```
target/site/jacoco/index.html
```

Abre ese archivo en tu navegador para ver el análisis detallado por clase, método y línea.

### Pruebas implementadas

#### `EstudianteServiceTest` — Pruebas Unitarias

Validan la lógica de negocio del servicio de forma aislada, usando **Mockito** para simular el repositorio:

| Test | Qué valida |
|---|---|
| `debeGuardarEstudiante()` | El servicio guarda correctamente y retorna el objeto creado |
| `debeListarEstudiantes()` | El servicio retorna la lista completa de estudiantes |

#### `EstudianteControllerTest` — Pruebas de Integración

Validan el controlador REST con **MockMvc** y **@WebMvcTest**, sin levantar el servidor completo:

| Test | Qué valida |
|---|---|
| `debeListarEstudiantes()` | `GET /api/estudiantes` retorna HTTP 200 con la lista en formato JSON correcto |

### Ejemplo de ejecución exitosa

```
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## ⚙️ Pipeline CI/CD

El proyecto usa **GitHub Actions** para automatizar el ciclo de integración y despliegue continuo. El pipeline se activa automáticamente en cada `push` o `pull request` a las ramas `main` y `develop`.

### Flujo del Pipeline

```
Push / Pull Request a main o develop
              │
              ▼
  ┌───────────────────────┐
  │  JOB 1: Build & Test  │  ←  ./mvnw clean verify
  │  (compilar + pruebas) │      Reporte JaCoCo publicado como artefacto
  └───────────────────────┘
              │ (si todas las pruebas pasan)
              ▼
  ┌───────────────────────┐
  │  JOB 2: Code Quality  │  ←  Verificación de cobertura mínima
  └───────────────────────┘
              │ (solo si el push es a main)
              ▼
  ┌───────────────────────┐
  │  JOB 3: Docker Build  │  ←  docker build + docker push a Docker Hub
  └───────────────────────┘
```

### Jobs del pipeline

| Job | Descripción | Se ejecuta en |
|---|---|---|
| `build-and-test` | Compila con Maven y ejecuta todas las pruebas JUnit | Todo push/PR a `main` y `develop` |
| `code-quality` | Verifica cobertura mínima de código con JaCoCo | Tras build exitoso |
| `docker-build` | Construye imagen Docker y publica en Docker Hub | Solo en push a `main` |

### Configuración del pipeline — `.github/workflows/ci-cd.yml`

```yaml
name: CI/CD - Gestión Académica

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  build-and-test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Configurar Java 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: maven

      - name: Compilar y ejecutar pruebas
        run: ./mvnw clean verify

      - name: Publicar reporte de cobertura
        uses: actions/upload-artifact@v4
        with:
          name: jacoco-report
          path: target/site/jacoco/

  docker-build:
    runs-on: ubuntu-latest
    needs: build-and-test
    if: github.ref == 'refs/heads/main'
    steps:
      - uses: actions/checkout@v4

      - name: Generar JAR
        run: ./mvnw clean package -DskipTests

      - name: Construir imagen Docker
        run: docker build -t gestionacademica:latest .
```

---

## 📁 Estructura del Proyecto

```
proyectFinal/
├── src/
│   ├── main/
│   │   ├── java/com/gestion/gestionacademica/
│   │   │   ├── GestionacademicaBillyApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── EstudianteController.java
│   │   │   │   ├── DocenteController.java
│   │   │   │   ├── CursoController.java
│   │   │   │   ├── MatriculaController.java
│   │   │   │   └── CalificacionController.java
│   │   │   ├── service/
│   │   │   │   ├── EstudianteService.java
│   │   │   │   ├── DocenteService.java
│   │   │   │   ├── CursoService.java
│   │   │   │   ├── MatriculaService.java
│   │   │   │   └── CalificacionService.java
│   │   │   ├── repository/
│   │   │   │   ├── EstudianteRepository.java
│   │   │   │   ├── DocenteRepository.java
│   │   │   │   ├── CursoRepository.java
│   │   │   │   ├── MatriculaRepository.java
│   │   │   │   └── CalificacionRepository.java
│   │   │   └── entity/
│   │   │       ├── Estudiante.java
│   │   │       ├── Docente.java
│   │   │       ├── Curso.java
│   │   │       ├── Matricula.java
│   │   │       └── Calificacion.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/com/gestion/gestionacademica/
│       ├── EstudianteServiceTest.java
│       └── EstudianteControllerTest.java
├── Dockerfile
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

