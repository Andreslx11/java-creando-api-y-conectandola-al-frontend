package com.aluracursos.screenmatch.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/*
Claro, aquí tienes un resumen más breve:
CorsConfiguration` es una clase utilizada en aplicaciones Java
 (específicamente en el contexto de Spring Framework) para
 configurar la política de CORS (Cross-Origin Resource Sharing),
 que permite controlar cómo los navegadores web manejan las solicitudes
  HTTP entre diferentes dominios. Se utiliza para especificar qué
   orígenes, métodos HTTP, encabezados y credenciales son permitidos
   en las solicitudes CORS.*/

/* Modelo 2.3.4   la anotación es para que identifique que una clase de confiiguración, e
*  implementa la interface  WebMvcConfigurer
 * */

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {



    /* Modelo 2.3.5
     * esto http://127.0.0.1:5500  se busca la que es para nosotros en  inspecionar en herramientas del desarrollador
     *  en la pagina de nuestro frontende, ese que inicializamos con live server,
     *
     * Como sabemos, está implementando allí un método de nuestra interfaz, que es la de addCorsMapping. Entonces está
     * creando allí un registro de ese CORS y está diciendo, ¿verdad? Aquí agregando un mapeo, nos está diciendo los
     * orígenes que vamos a aceptar en este caso de nuestro front-end. Entonces ese puerto 127.0.0.1.5.5.0.1. Si en tu
     *  caso es 5.5.0.0, puedes venir aquí y poner 5.5.0.0. O si es otro puerto, también allí puedes poner el número del
     *  puerto. Y aquí lo que está diciendo es que da permiso para que realice ciertos métodos, el get, post, put, delete,
     *  todos los métodos que habíamos visto allá en nuestro Postman. Ya allí está dando ese permiso para esas solicitudes
     *  entre esos tipos de métodos. Una vez hecho esto, voy a ejecutar nuevamente la aplicación aquí para recordar que
     *  se hagan efectivos los cambios
    * */
    // cumpliento del contrato de la interface WebMvcConfigurer
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }


}
