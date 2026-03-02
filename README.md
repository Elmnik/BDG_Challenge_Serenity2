# Java Serenity BDD Automation Framework

Este proyecto contiene una solución de automatización que abarca pruebas de API y pruebas de UI,
utilizando el patrón de diseño **Screenplay** con **Serenity BDD**.

## 🚀 Tecnologías Utilizadas
* **Java 17**
* **Gradle**
* **Cucumber** (Gherkin para definición de escenarios)
* **Serenity BDD** (Gestión de reportes y abstracción de Screenplay)
* **Screenplay Pattern** (Actores, Tareas, Interacciones, Preguntas)
* **RestAssured** (Para la automatización de API)
* **Selenium WebDriver** (Para la automatización de UI)

---

## 🛠 Estructura del Proyecto (Multiproyecto)

El repositorio se divide en dos módulos principales:

### 1. `api-automation`
Automatización de flujos en `https://jsonplaceholder.typicode.com/`.
* **Interactions:** Implementación de `RestInteraction`.
* **Step Definitions:** Uso de `seeThatResponse` con aserciones **JsonPath**.
* **Features:** Escenarios para Listar, Registrar, Actualizar y Eliminar usuarios.

### 2. `ui-automation`
Automatización de flujos web en `https://www.saucedemo.com`.
* **Escenarios:** Login exitoso/fallido.
* **Flujo de Compra**
* **Gestión del Carrito** Añadir/eliminar múltiples productos.
* **Arquitectura:** Basado en Screenplay.

---

## ⚙️ Configuración y Ejecución

### ⚙️ Requisitos Previos
* Java JDK 17 configurado.
* Gradle instalado (o usar el `gradlew` incluido).

### Ejecutar Pruebas de API & UI
```bash
JAVA_HOME=$(/usr/libexec/java_home -v 17) ./gradlew clean test aggregate

###  Ejecutar por Módulo
API:
./gradlew :api-automation:clean :api-automation:test :api-automation:aggregate

UI (Headless por defecto en CI):
./gradlew :ui-automation:clean :ui-automation:test :ui-automation:aggregate -Dheadless=true
---

### Nota Tecnica
El Proyecto ha sido diseñado y optimizado en primera instancia para equipos macOS
con arquitectura Apple Silicon (M Series)

