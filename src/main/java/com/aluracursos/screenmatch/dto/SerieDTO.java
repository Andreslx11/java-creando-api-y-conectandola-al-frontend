package com.aluracursos.screenmatch.dto;

import com.aluracursos.screenmatch.model.Categoria;





/* Modulo 2.2.1 ==> serieController 2.2.2DTO, que es Data Transfer Object (Objeto de
 * Transferencia de Datos)
 *
 *  los campos del record son atributos de la clase
 *  no se trajo todo el enum o lista de espisodios
 * */



public record SerieDTO(
                         String titulo,
                         Integer totalTemporadas,
                         Double evaluacion,
                         String poster,
                         Categoria genero,
                         String actores,
                         String sinopsis
) {
}
