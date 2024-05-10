package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {
/*Modulo 4.1.4 es para el motodo buscarSeriePorTitulo() de class principa 4.1.3
    IMPORTANTE ESTO ES UNA CONSULTA DEREIVADA spring data JPA
    IMPORTANTE   este Titulo  de  findByTitluloContainsIgnorCase   es nombre
    del atributo que tiene en nuestra clase por el cual vamos hacer la consulta  y esta iniciando en
    mayuscula por esta siendo concatenado por la convenciones,
    y eso con concatenación  findByTitluloContainsIgnorCase  es metodos de spring Data JPA
    de consulta a bases de datos -- buscar documentación , ignoreCase es para que ignore entre mayusculas y
    minisculas, el campo es String que va ser lña serie buscada por nuestro usuario
     */

    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);

    List<Serie> findTop5ByOrderByEvaluacionDesc();

    List<Serie> findByGenero(Categoria categoria);

    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.evaluacion >= :evaluacion")
    List<Serie> seriesPorTemparadaYEvaluacion(int totalTemporadas, Double evaluacion);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreEpisodio%")
    List<Episodio> episodiosPorNombre(String nombreEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5 ")
    List<Episodio> top5Episodios(Serie serie);


    /*Modulo 3.3.1  ==> SerieService 3.3.2
     metodo para que nos retorne el episodio
    * mas reciente de una serie*/
    @Query("SELECT s FROM Serie s " + "JOIN s.episodios e " + "GROUP BY s " + "ORDER BY MAX(e.fechaDeLanzamiento) DESC LIMIT 5")
    List<Serie> lanzaminetosMasRecinetes();

}
