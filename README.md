# java-spring-template

Este proyecto tiene como finalidad actuar de template para las futuras APIs.

Se agregaron las dependencias "básicas" necesarias.

Para usar el proyecto se deben cambiar un par de cosas.

Buscar en el proyecto "demo" (sin case sensitive) y reemplazar por el nombre del proyecto final.

El paquete default de la app también hay que cambiarlo. Hoy es: "com.marb.demo"

Así mismo hay que cambiar la configuración de logback para que apunte al nuevo paquete, las properties de liquibase, el pom.xml, etc.

También encontraran bajo el paquete <i>"com.marb.modude.demo"</i> un pequeño ejemplo de una CRUD REST funcional.

Solo deben levantar la app y acceder a localhost:8081/demo (get/post), localhost:8081/demo/{id} (get, put, delete).

Así mismo se dejo configurado <a href="https://swagger.io/"><b>Swagger</b></a> para que en http://localhost:8081/swagger-ui.html tengan documentado los endpoints y puedan usar swagger para probar la api.

