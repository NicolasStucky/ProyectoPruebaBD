# ProyectoPruebaBD

Este proyecto es una guía práctica para probar la conexión a una base de datos MySQL y ejecutar la operación **Select** del CRUD utilizando el patrón **DAO (Data Access Object)**. El código está organizado para separar las responsabilidades de acceso a datos, la lógica de dominio y la configuración de la conexión.

## Tecnologías

- Java 21
- Maven
- MySQL Connector/J 8.3.0
- Base de datos MySQL (local)

## Estructura del proyecto

```
src/main/java/prueba_bd
├── conexion
│   └── Conexion.java
├── datos
│   ├── IJugadorDAO.java
│   └── JugadorDAO.java
└── dominio
    └── Jugador.java
```

### `prueba_bd.conexion`
Contiene la clase `Conexion`, responsable de crear el `Connection` contra la base de datos. Incluye un `main` para ejecutar una prueba rápida de conexión.

### `prueba_bd.dominio`
Define la entidad `Jugador`, un POJO que representa las filas de la tabla `jugadores` con campos para el id, nombre y apellido.

### `prueba_bd.datos`
Implementa el patrón DAO:
- `IJugadorDAO` define la operación `listarJugadores()`.
- `JugadorDAO` implementa la interfaz y ejecuta el `SELECT` usando JDBC. La clase también posee un `main` que imprime por consola los registros retornados.

## Flujo de la aplicación

1. **Configuración de la conexión**: `Conexion.getConexion()` arma la URL JDBC, usuario y contraseña, y abre la conexión usando el driver de MySQL. Si ocurre algún error lo informa en consola. 【F:src/main/java/prueba_bd/conexion/Conexion.java†L4-L34】
2. **Modelo de dominio**: `Jugador` encapsula los datos obtenidos de la consulta (`idjugador`, `jugadornombre`, `jugadoresapellido`) y provee métodos `get/set`, así como `toString`, `equals` y `hashCode`. 【F:src/main/java/prueba_bd/dominio/Jugador.java†L5-L70】
3. **Acceso a datos (DAO)**:
   - `JugadorDAO.listarJugadores()` solicita una conexión a `Conexion`, prepara la sentencia `SELECT * FROM jugadores ORDER BY idjugador`, y recorre el `ResultSet` para llenar una lista de `Jugador`. Todos los recursos se cierran en un bloque `finally`. 【F:src/main/java/prueba_bd/datos/JugadorDAO.java†L13-L56】
   - El método `main` del DAO invoca `listarJugadores()` y muestra cada jugador en consola, sirviendo como prueba del flujo completo. 【F:src/main/java/prueba_bd/datos/JugadorDAO.java†L59-L63】

## Requisitos de base de datos

Asegúrate de contar con una base de datos MySQL llamada `prueba_bd` (o ajusta la constante en `Conexion.java`) y una tabla `jugadores` con al menos las columnas:

```sql
CREATE TABLE jugadores (
    idjugador INT PRIMARY KEY,
    jugadornombre VARCHAR(100),
    jugadoresapellido VARCHAR(100)
);
```

## Cómo ejecutar las pruebas

1. **Probar solo la conexión**: Ejecuta la clase `Conexion` (por ejemplo, desde tu IDE) para verificar que los parámetros sean correctos. 【F:src/main/java/prueba_bd/conexion/Conexion.java†L26-L34】
2. **Listar jugadores**: Ejecuta el `main` de `JugadorDAO` para correr el `SELECT` y mostrar los resultados en consola. 【F:src/main/java/prueba_bd/datos/JugadorDAO.java†L59-L63】

> **Nota:** El proyecto no implementa operaciones de inserción, actualización o eliminación; se centra exclusivamente en el flujo de lectura (`Select`) del CRUD siguiendo el patrón DAO.

## Configuración

Si necesitas cambiar los parámetros de conexión (URL, usuario, contraseña o base de datos), edita los valores definidos en `Conexion.getConexion()`. 【F:src/main/java/prueba_bd/conexion/Conexion.java†L12-L20】

## Ejecución con Maven

Si prefieres compilar y ejecutar desde la terminal:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="prueba_bd.datos.JugadorDAO"
```

Para utilizar `mvn exec:java` añade el plugin `exec-maven-plugin` al `pom.xml` o ejecuta las clases directamente desde tu IDE.

