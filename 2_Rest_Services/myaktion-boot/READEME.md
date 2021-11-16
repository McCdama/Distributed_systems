// application.properties in ressource folder
#h2
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.generate-unique-name=false


 gradle bootRun


http://localhost:8080/h2-console
--> write in the JDBC URL field: jdbc:h2:mem:testdb 


SWAGGER UI
http://localhost:8080/swagger-ui.html