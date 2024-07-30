
# Pizzería API

Este proyecto es una API para gestionar una pizzería, desarrollada con Spring Boot y Maven. La API permite gestionar pizzas, ingredientes, clientes y órdenes de manera eficiente.

![Imagen](https://img.freepik.com/free-photo/freshly-baked-pizza-rustic-wooden-table-generated-by-ai_188544-151741.jpg?t=st=1722374286~exp=1722377886~hmac=bbf76caf5662369550740c4484c80c3810a0382f20a12bbddd598e1cfcb21ac0&w=1380)

## Requisitos

-   Java 17 o superior
-   Maven 3.6.3 o superior

## Instalación

Sigue los siguientes pasos para instalar y configurar el proyecto:

1.  **Clona el repositorio:**
    

    `git clone https://github.com/tu-usuario/pizzeria-api.git` 
    
2.  **Navega al directorio del proyecto:**

    
    `cd pizzeria-api` 
    
3.  **Compila el proyecto:**
    

    `mvn clean install` 
    

## Uso

Una vez instalado, puedes ejecutar la aplicación con el siguiente comando:



`mvn spring-boot:run` 

La aplicación estará disponible en `http://localhost:8080`.


## Documentación de la API con Swagger

La API incluye Swagger para la documentación interactiva. Una vez que la aplicación esté en funcionamiento, puedes acceder a la documentación completa de la API en `http://localhost:8080/swagger-ui.html`.

## Configuración

El archivo de configuración principal se encuentra en `src/main/resources/application.properties`. Puedes modificar este archivo para cambiar la configuración de la base de datos y otros ajustes de la aplicación.

### Ejemplo de configuración para una base de datos H2:
```
spring.datasource.url=${DATABASE_URL}  
spring.datasource.username=${DATABASE_USERNAME}  
spring.datasource.password=${DATABASE_PASSWORD}
```

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para obtener más información.
