# Examen de Automatización de Pruebas

Este repositorio contiene el código fuente y la configuración del pipeline de despliegue continuo para el Examen Final de la asignatura Automatización de Pruebas.

## Descripción del Proyecto

El proyecto consiste en una aplicación Java con Spring Boot (Maven) gestionada mediante un flujo de Integración y Despliegue Continuo (CI/CD) orquestado en Jenkins. El objetivo es demostrar la implementación de un pipeline que asegure la calidad del software, utilizando contenedores Docker para entornos efímeros y estrategias de recuperación (Rollback).

## Tecnologías Utilizadas

* Java 17
* Maven
* Jenkins
* Docker

## Estrategia de Pruebas Implementada

El pipeline ejecuta tres niveles de pruebas automatizadas para garantizar la estabilidad:

1.  Pruebas Unitarias (Unit Tests):
    * Herramientas: JUnit 5 + Mockito.
    * Objetivo: Validar la lógica de negocio de forma aislada (Clase `UserService`).

2.  Pruebas de Integración (Integration Tests):
    * Objetivo: Verificar la interacción entre componentes sin Mocks.

3.  Pruebas de Aceptación (Acceptance Tests / BDD):
    * Herramientas: Cucumber + Gherkin.
    * Objetivo: Validar flujos de negocio críticos (End-to-End) contra el entorno desplegado.
    * Acceptance Gate: Estas pruebas actúan como una barrera de calidad; si fallan, el despliegue a producción se cancela.

## Pipeline de Despliegue

El archivo `Jenkinsfile` define las siguientes etapas:

1.  Build: Compilación y empaquetado del artefacto `.jar`.
2.  Unit & Integration Tests: Validación temprana del código.
3.  Deploy to Staging: Despliegue automático de la aplicación en un contenedor Docker (Puerto 8090).
4.  Acceptance Gate: Ejecución de escenarios BDD contra el entorno de Staging.
5.  Deploy to Production: Despliegue final tras aprobación manual.
6.  Rollback Automático: En caso de fallo en cualquier etapa crítica, se puede configurar ejecutar otros procesos  para restaurar el estado estable.

## Cómo Ejecutar Localmente

Para replicar las pruebas en un entorno local, utilizar los siguientes comandos:

- Sin Docker
```bash
# Compilar y ejecutar la aplicación
mvn clean package -DskipTests
java -jar target/automation-pipeline-1.0.0-SNAPSHOT.jar --server.port=8090
```
- Con Docker
```bash
docker build -t mi-app-local .
docker run -d --name mi-contenedor-app -p 8090:8080 mi-app-local
```
- Ejecuccion de Pruebas
```bash
# Ejecutar Pruebas Unitarias
mvn test -Dtest=!RunCucumberTest

# Ejecutar Pruebas de Integración
mvn verify -DskipUnitTests -Dtest=!RunCucumberTest

# Ejecutar Pruebas de Aceptación (Requiere la app levantada previamente)
mvn test -Dtest=RunCucumberTest
```
