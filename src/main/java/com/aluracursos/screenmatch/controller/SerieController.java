package com.aluracursos.screenmatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/*Modelo 1.3.1
esto es siguiendo el modelo MVC que en este paso es una clase con anotación controler
es para que nos sirva de intermediario entre el lo view frontend y el model lo del
backend lo que hicimos la modelación del backend
IMPORTANTE CADA DATO TRATADO TIENE QUE TENER UN CONTROLER EN ESTE CASO SerieControles


se usan anotaciones de spring y como estamos trabajando  nuestra app con el modelo Rest para
eso la anotación @RestController para identificar que estamos trabajando con ese modelo */
@RestController
public class SerieController {


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
    @GetMapping("/series")
    public String mostrarMensaje(){
        return "Este es mi primer mensaje en mi aplicación web";
    }
}
