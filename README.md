# java-spring-template

Este proyecto tiene como finalidad actuar de template para las futuras APIs.

Se agregaron las dependencias "basicas" necesarias.

Para usar el proyecto se deben cambiar un par de cosas.

Buscar en el proyecto "demo" (sin case sensitive) y reemplazar por el nombre del proyecto final.

El paquete default de la app tambien hay que cambiarlo. Hoy es: "com.marb.demo"

Asi mismo hay que cambiar la configuracion de logback para que apunte al nuevo paquete, las properties de liquibase, el pom.xml, etc.

Tambien encontraran bajo el paquete "com.marb.modude.demo" un pequeño ejemplo de una CRUD REST funcional.

Solo deben levantar la app y acceder a localhost:8081/demo (get/post), /demo/{id} (get, put, delete).

Asi mismo se dejo configurado <b>Swagger</b> para que en http://localhost:8081/swagger-ui.html tengan documentado los endpoints y puedan usar swagger para porbar la api.

