# API de Recursos Humanos

## Descripción

Esta API proporciona una serie de endpoints para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en
diferentes entidades relacionadas con la gestión de recursos humanos en una organización. La API está construida
utilizando Spring Boot, Java 17, MySql y docker.

## Autenticación

La API utiliza autenticación basada en tokens. Los usuarios deben incluir un token de autorización válido en el
encabezado de sus solicitudes para acceder a los endpoints protegidos.

## Documentación y Pruebas

La documentación completa de la API, incluyendo detalles sobre los endpoints, parámetros de solicitud y respuestas
esperadas, está disponible en Swagger UI. Puedes acceder a la documentación
en [Swagger UI](http://localhost:8080/swagger-ui/index.html#/).

## Endpoints Principales

### Usuarios

- **GET /api/allUser**: Obtiene todos los usuarios registrados.
- **POST /api/user**: Crea un nuevo usuario. este es administrador esta necesita un password para acceder a los otros
  endpoints
- **POST /api/user/employed**: Crea un nuevo usuario empleado.
- **GET /api/user/{id}**: Obtiene un usuario por su ID.
- **PUT /api/user/{id}**: Actualiza un usuario existente por su ID.
- **DELETE /api/user/{id}**: Elimina un usuario existente por su ID.

### Áreas

- **GET /api/allAreas**: Obtiene todas las áreas registradas.
- **GET /api/area/{id}**: Obtiene un área por su ID.
- **POST /api/area**: Crea una nueva área.
- **PUT /api/area/{id}**: Actualiza un área existente por su ID.
- **DELETE /api/area/{id}**: Elimina un área existente por su ID.

### Documentos

- **GET /api/allDocuments**: Obtiene todos los documentos registrados.
- **POST /api/document**: Crea un nuevo documento.
- **DELETE /api/document/{id}**: Elimina un documento existente por su ID.

### Roles

- **POST /api/rol**: Crea un nuevo rol.
- **PUT /api/rol/{id}**: Actualiza un rol existente por su ID.

### Asignaciones de Usuario a Rol

- **GET /api/Todo**: Obtiene todas las asignaciones de usuario a rol.
- **POST /api/Todo**: Crea una nueva asignación de usuario a rol.

## Requisitos

Para utilizar esta API, es necesario contar con permisos de acceso y autenticación mediante tokens de autorización.
Asegúrate de incluir el token de autorización en el encabezado de tus solicitudes asegurate de registrarte primero en la
apliacion para poder usar los demas endpoints esta ruta.

- **POST /api/user**: Crea un nuevo usuario.

## Ejemplos de Uso

A continuación se presentan algunos ejemplos de cómo interactuar con la API utilizando herramientas como cURL o Postman:

### Ejemplo con Curl

```bash
curl -X GET -H "Authorization: Bearer {tu_token}" http://localhost:8080/api/allUser

curl -X DELETE http://localhost:8080/api/document/123

curl -X POST -H "Content-Type: application/json" -H "Authorization: Bearer {tu_token}" -d '{"name": "John", "lastname": "Doe", "email": "john@example.com", "psw": "password123", "phone": "1234567890", "status": 1}' http://localhost:8080/api/user

```

### Base de datos Normalizada

![dark](./Doc/recursos-humanos.png)

### Publicación de la api en docker

![dark](./Doc/docker-composer.png)

![dark](./Doc/docker-up.png)

- Si todo sale bien se les debe crear un contenedor con los recursos de la api para su funcionamiento

![dark](./Doc/deploy.png)
