package com.aluracursos.screenmatch.controller;

import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/*Modelo 1.3.1
esto es siguiendo el modelo MVC que en este paso es una clase con anotación controler
es para que nos sirva de intermediario entre el lo view frontend y el model lo del
backend lo que hicimos la modelación del backend
IMPORTANTE CADA DATO TRATADO TIENE QUE TENER UN CONTROLER EN ESTE CASO SerieControles


se usan anotaciones de spring y como estamos trabajando  nuestra app con el modelo Rest para
eso la anotación @RestController para identificar que estamos trabajando con ese modelo */
@RestController
public class SerieController {

    /*Modulo 2.1.0  necesitamos inyectar esa dependencia de nuestro repositorio */
    @Autowired
    private SerieRepository repository;

    /*modelo 1.3.2   @GetMapping para mapear obter datos de una ruta en especifico
   aquí es get quiere decir para mapiar los datos para obtenerlos
  * son metodo del modelo Rest como GET, POST,  PUT, PATCH, DELETE, en el postman se ven
  GET para obtener datos.
  POST para enviar nuevos datos.
  PUT para actualizar datos existentes.
  DELETE para eliminar datos.

   IMPORTANTE  en el campo es el end point, esa ruta queremos mapear para que suceda alguna cosa,

   es para un ejemplo en buscador dariamos la ruta http://localhost:8081/series y nos saldria el
   mensaje
   */
    /*Modelo 2.1.1 se comenta era ejemplo */
//    @GetMapping("/series")
//    public String mostrarMensaje(){
//        return "Este es mi primer mensaje en mi aplicación web";
//    }

    /*Modulo 2.1.2  lo queremos retornar e una lista de series al frontend */
    /*Modulo 2.1.2   ==> SerieDTO 2.2.1 se crea el package DTO, que es Data Transfer Object (Objeto de
    * Transferencia de Datos) donde vamos crear todos nuestros dto//
          se presenta un problama que es mismo de lo recrod
    cuando queremos mapear algo de la api de donde estamos trayendola infromacion
    al no tomar todos lo campos y lo que susa @JacsonIngore para que ignore los
    campos que no queremos, ahora que se trata de retornar la frontend para algo igual
    pero esto por ejemplo series que no tienen los mismo campos
    * problema, si quisiera, podría... Como sé que hay algún problema de
    * serialización, sobre todo con esa referencia circular de episodios,
    *lo que podría hacer es utilizar esa anotación
    * que habíamos utilizado anteriormente, la JSON Ignore, para que ignore
    *  esa cosa de los episodios y no se muestre. Pero existe una alternativa
    *  que consideramos un poco mejor. ¿Por qué? Porque existe un patrón de
    * diseño, que es el llamado DTO, que es Data Transfer Object (Objeto de
    * Transferencia de Datos), que ya vamos a hablar un poco más de él, pero
    *  que nos va a permitir tener una representación de nuestro objeto y nos
    *  va a ayudar también a mostrar simplemente las informaciones que son útiles
    * allí para nuestros usuarios. E incluso nos va a proteger de no mostrar
    * informaciones sensibles
    Imaginemos que queremos mostrar una cosa aquí de login, imaginémonos un
    escenario de login, donde quiero mostrar solamente los datos de un usuario,
    por ejemplo, pero no quiero mostrar las contraseñas. Si muestro toda mi
    entidad allá, que solamente tiene dos campos de nombre y contraseña,
    todo el mundo va a saber la contraseña, ¿no? Quiero simplemente mostrar
    un ejemplo, el nombre de usuario, que también no les estoy diciendo aquí,
    ahí es una buena práctica, vamos a hacer, sino dando un ejemplo, allí
    tendríamos como proteger los datos utilizando este DTO. Entonces,
    eso es lo que vamos a hacer a continuación, así que sigan aquí
    junto conmigo.*/
    /*modulo 2.2.2 dr comenta por que ya queremo usar el record SerieDTO*/
//    @GetMapping("/series")
//    public List<Serie> obtnerTodasLasSeries(){
//        return repository.findAll();
//    }

    /*Modulo 2.2.2 ==> CorsConfiguration 2.3.3
    * findALl() nos devuelve una lista del tipo dato Serie pero lo que ahora queremos
    * es que retorne una lista de tipo dato SerieDTO para eso usamos stream(), y los get recordar son
    * los automaticos que genera el record SerieDTO por campos del mismo,
    * map para convertir cada  serie en una nueva SerieDTO
    *
    * ==> RUN y ver en navegador localhost:8081/series    si se esta cargando los datos se como un  tipo json*/
    @GetMapping("/series")
    public List<SerieDTO> obtnerTodasLasSeries(){
        return repository.findAll().stream()
                .map(s -> new SerieDTO(s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(),
                        s.getPoster(),s.getGenero() ,s.getActores(), s.getSinopsis()))
                .collect(Collectors.toList());
    }


//    /*como todo estaba cargado, se hizo este metodo para ensayar la configurancio
//      del   spring-boot-devtools la dependencia y ya configurada en setting para que el
//      servidor tomcat autocargue los cambios y no se toque estar haciendo run*/
//    @GetMapping("/inicio")
//    public String muestraMensaje(){
//        return "provando liveReloadind";
//    }
}
