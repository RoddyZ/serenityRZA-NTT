## Pasos para la ejecución del escenario de prueba
(El detalle lo encuentras en el PDF adjunto: Roddy-Zamora-PruebaSerenity)

### 1. Prerrequisitos

- Descargar, instalar y configurar la variable de entorno de **Java 11** y **Maven**.
- Descargar e instalar **IntelliJ IDEA** con los plugins de **Cucumber for Java** y **Gherkin**.
- Descargar e instalar **Git**.

### 2. Instalación de dependencias

Abrir una consola de Git en el directorio del proyecto de Serenity y ejecutar los siguientes comandos:

```bash
mvn clean package -U
mvn clean install -DskipTests
```

### 3. Instalación de dependencias

En la misma consola que ejecutamos los comandos anteriores procedemos a correr la prueba mediante el siguiente comando:

```bash
mvn clean verify -Dcucumber.filter.tags="@FlujoCompras"
```

### 4. Instalación de dependencias

El reporte con las capturas de pantalla se podra encontrar en \serenity-cucumber-starter-master\target\site\serenity\index.html
