# Rest API Web Service for CRUD Images of a given user

Usé el contract first aproach escribiendo la descripción de la API a implementar usando OPENAPI como se puede ver en el documento 

openapi-images.yml

Implemente la arquitectura siguiendo las restricciones de REST y usando la arquitectura en capas: controladores, servicios y acceso a datos

Realice los test para cada una de las capas, probando tanto happy paths como la respuesta del sistema ante situaciones problematicas como intentar editar una imagen que no existe 
También añadí un test "e2e" donde uso el cliente WebTestClient de SpringBoot para realizar peticiones y analizar sus respuestas.

Clean Code

Se ha usado técnicas de código limpio como la separación de asuntos, métodos de una única responsabilidad, 
nombrado significativo de variables y métodos para hacer código legible disminuyendo la cantidad de comentarios en la mayor parte del código

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

![captura pantalla swaggerui](./captures/swaggerui.png?raw=true)

Se muestra a continuación ejemplos con valores adecuados para probar la aplicación.
Para poder executar cada peticion hay que pulsar previamente un botón de Try it Out

### Consultar imagenes de un usuario

![ejemplo peticion get](./captures/get.png?raw=true)

### Añadir una imagen asociada a un usuario

![ejemplo peticion post](./captures/post.png?raw=true)

### Editar una imagen asociada a un usuario

![ejemplo peticion put](./captures/put.png?raw=true)

### Borrar una imagen asociada a un usuario

![ejemplo peticion delete](./captures/delete.png?raw=true)


También se puede probar la aplicación con cualquier cliente que permita peticiones http como curl, postman,...

## Ampliaciones/Mejoras

La prioritaria sería incluir gestión de usuarios, lo que permitiría, entre otras funcionalidades poder comprobar por ejemplo que sólo se puede añadir una imagen con un id de usuario existente

Gestionar desde el servicio la creación de nuevos id para imágenes en el servicio en vez de delegarlo a la BD

Creación de un cliente para tener un interfaz gráfico para un usuario final con tecnologías como React, Angular, Vue o similares.

## Autor

Eduardo Garcia Ibaseta
