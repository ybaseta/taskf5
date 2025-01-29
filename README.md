# Rest API Web Service for CRUD Images of a given user

Usé el contract first aproach escribiendo la descripción de la API a implementar usando OPENAPI como se puede ver en el documento 

openapi-images.yml

Implemente la arquitectura siguiendo las restricciones de REST y usando la arquitectura en capas: controladores, servicios y acceso a datos

Realice los test para cada una de las capas, probando tanto happy paths como la respuesta del sistema ante situaciones problematicas como intentar editar una imagen que no existe 
También añadí un test "e2e" donde uso el cliente WebTestClient de SpringBoot para realizar peticiones y analizar sus respuestas.

## Tecnologías

SpringBoot 3.4.2 que a su vez te fija la versión de Spring 6

Java 21 por ser la más reciente versión LTS

H2 como base de datos en memoria

Spring Data

SpringDoc OpenAPI que genera un interfaz visual swagger UI que sirve como documentación interactiva que permite probar los endpoints


## Instalación y prueba de la aplicación

Para ejecutar la aplicacíon he creado un archivo jar taskf5.jar

Para poder ejecutarlo el único requisito es tener instalado en el equipo la máquina virtual de Java 21 o superior

Para ejecutarlo en el directorio donde se encuentre --> java -jar taskf5.jar

A partir de ese momento habrá un servidor tomcat en el puerto 8080 atendiendo las peticiones

El programa genera una documentación que funciona también como cliente interactivo (Swager UI) que es muy conocida y usada en la industria

Se accede a ella, en esta dirección http://localhost:8080/swagger-ui/index.html

También se puede probar la aplicación con cualquier cliente que permita peticiones http como curl, postman,...

![ejemplo peticion get] (./captures/get.png?raw=true)


## Autor

Eduardo Garcia Ibaseta
