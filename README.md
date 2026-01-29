# BDG QA Challenge - (API & UI)

Este proyecto contiene una solución de automatización integral que abarca pruebas de API (ReqRes) y pruebas de Interfaz de Usuario (SauceDemo), utilizando el patrón de diseño **Screenplay** con **Serenity BDD**.

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
Automatización de flujos en `https://reqres.in`.
* **Interactions:** Implementación de `RestInteraction`.
* **Models:** Mapeo de respuestas JSON a objetos Java (`UserData`).
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
- JAVA_HOME=$(/usr/libexec/java_home -v 17) ./gradlew :api-automation:clean :api-automation:test :api-automation:aggregate
- JAVA_HOME=$(/usr/libexec/java_home -v 17) ./gradlew :api-automation:clean :api-automation:test :api-automation:aggregate -Dcucumber.glue="com.bdg.api.stepdefinitions" --continue 
- JAVA_HOME=$(/usr/libexec/java_home -v 17) ./gradlew :ui-automation:clean :ui-automation:test :ui-automation:aggregate
- JAVA_HOME=$(/usr/libexec/java_home -v 17) ./gradlew :ui-automation:clean :ui-automation:test :ui-automation:aggregate -Dheadless=true

---

### Nota Tecnica
El Proyecto ha sido diseñado y optimizado en primera instancia para equipos macOS con arquitectura Apple Silicon (M Series)

Debido a las particularidades de gestión de memoria y el compilador de Java en estas arquitecturas, se han configurado versiones específicas de dependencias (como Lombok 1.18.30) y rutas de ejecución para garantizar la compatibilidad con el JDK 17+ y evitar conflictos de ClassLoader. Si se ejecuta en otras arquitecturas, asegúrese de tener correctamente configurada la variable de entorno JAVA_HOME