package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.dto.EpisodioDTO;
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

    /*Modulo 4.2
    * se uso lenguaje consulta de java JPQL
    * al crear el metodo con intellij nos da <EpisodioDTO> pero nosotros
    * queremos retornar  Episodio nuestra Entity
    *
    * La razón por la que se cambió de EpisodioDTO a Episodio en la consulta JPQL es la siguiente:
    *
    Entidad vs DTO: Normalmente, cuando se trabaja con una API REST, se utiliza un DTO (Data Transfer Object)
    *  para transferir los datos entre las capas de la aplicación. Los DTOs ayudan a mantener la separación de
    *  responsabilidades y evitan exponer directamente las entidades a los controladores.
    *
    Consulta JPQL: Sin embargo, en este caso, la consulta JPQL se está ejecutando directamente en el repositorio,
    *  que es la capa más cercana a la base de datos. Aquí, es más apropiado utilizar la entidad Episodio
    * directamente, ya que la consulta está obteniendo los datos de la base de datos y no necesita realizar
    * ninguna transformación adicional.
    *
    Eficiencia: Al utilizar la entidad Episodio en la consulta JPQL, se evita la necesidad de realizar una
    * conversión adicional de EpisodioDTO a Episodio en el servicio. Esto hace que la consulta sea más
    *  eficiente y evita pasos innecesarios en el procesamiento de los datos.
    *
    En resumen, el cambio de EpisodioDTO a Episodio en la consulta JPQL se debe a que, en este caso,
    * es más apropiado trabajar directamente con la entidad de base de datos, ya que la consulta se está ejecutando
    *  en el repositorio. Esto mejora la eficiencia y simplifica el proceso de obtención de los datos.
        * */
    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numeroTemporada")
    List<Episodio> obtenerTemporadaPorNumeros(Long id, Long numeroTemporada);
}
