package com.aluracursos.screenmatch.service;


import com.aluracursos.screenmatch.dto.EpisodioDTO;
import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*Modulo 3.1.1 ==> 3.1.2 /crear la class
 se crea para evitar que la SerieController haga tantas cosas y hasi se pierda
 la programcion orientada objetos.
 //
 Dicho esto, es importante también destacar que tenemos otra anotación dentro
 de Spring que es la anotación de service. Incluso ya tenemos un paquete service
 dentro de nuestra aplicación que era donde teníamos todas aquellas clases que
  cuidaban un poco más de las reglas de negocio, las reglas de negocio ahí
 determinadas para nuestro ScreenMatch. Y vamos entonces a continuar utilizando
 esa misma metodología de servicio para aplicar aquí en nuestra aplicación
 web. ¿Por qué? Es importante destacar que, por ejemplo, voy a mostrarles
 aquí, estamos nuevamente en IntelliJ dentro de nuestro controller. El controller,
 es el que tiene ese contacto directo con nuestro navegador. Si nosotros
 colocamos aquí otras responsabilidades como, por ejemplo, llamar ese
 repository, hacer esa transformación de los datos, allí perdemos un poco de
 la esencia de la programación orientada a objetos y también de aquella
 cuestión de responsabilidad única. Entonces ya nuestro controller no estaría
 simplemente encargado por mantener esa conexión con el navegador, sino también
 de transformar los datos, de llamar al repository. No es eso lo que queremos,
 ¿verdad? Queremos realmente aprovechar todas las buenas prácticas y uno
 de los principios de Java, que es la baja, bajo acoplamiento y alta
 cohesión. Entonces, siguiendo estos principios, vamos a utilizar nuevamente
 nuestro paquete de servicios.
*/


/*Modulo 3.1.2 ==> SerieController 3.1.3
* La anotación `@Service` en Java se usa para marcar una clase como
*  un servicio en una aplicación Spring. Esto proporciona una forma
*  de identificar y gestionar fácilmente los componentes de la lógica
*  de negocio en una aplicación Spring.*/
@Service
public class SerieService {




    /*Modulo 3.1.5  /inyeccion de dependencia /  también se trabajo para que SerieController
    no tubiera acceso directo a nuestro repository
    */

    @Autowired
    private SerieRepository repository;


    //Modulo 3.1.5 ==> SerieController 3.1.6
    public List<SerieDTO> obtnerTodasLasSeries(){
        return convierteDatos(repository.findAll()) ;
    }

    /*Modulo 3.2.2   esto se puede como 3.1.5 desde .stream iguañ todo pero para evitar
    * duplicar codigo se crea el metodo convierte datos Modulo 3.2.3 que conviente una lista se
    * Serie en SerieDTO  como campo parametro toma la Serie de la base datos con la consulta
    *  findTop5ByOrderByEvaluacionDesc() */
    public List<SerieDTO> obtenerTop5(){
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }



    /*Modulo 3.3.2  ==> SeriesController 3.3.3*/
    public List<SerieDTO> obtenerLanzamientosMasRecientes(){
        return convierteDatos(repository.lanzaminetosMasRecinetes());
    }


    /*Modulo 3.2.3 para evitar duplicar codigo se creo este metodo para tratar datos
    * de Serie  a SerieDTO*/
    private List<SerieDTO> convierteDatos(List<Serie> serie){
            return   serie.stream()
                    .map(s -> new SerieDTO(s.getId(),s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(),
                            s.getPoster(),s.getGenero() ,s.getActores(), s.getSinopsis()))
                    .collect(Collectors.toList());
    }

    /*modulo 3.4.2
    *
    * explicar paso a paso este código:

    public SerieDTO obtenerPorId(long id) {

    Este es el método público que recibe un parámetro id de tipo long y retorna un objeto SerieDTO.
    Optional<Serie> serie = repository.findById(id);
    Aquí se utiliza el método findById() del repositorio (probablemente una instancia de JpaRepository)
    para buscar una entidad Serie por su id.
    El resultado de esta búsqueda se almacena en una variable serie de tipo Optional<Serie>.
    Optional es una clase que envuelve un valor y permite manejar la posibilidad de que ese valor sea null.
    if(serie.isPresent()) {
    Se verifica si el Optional serie contiene un valor (es decir, si se encontró una Serie con el id proporcionado).
    Si el Optional contiene un valor, se ejecuta el bloque de código dentro de este if.
    Serie s = serie.get();
    Si el Optional serie contiene un valor, se extrae ese valor y se asigna a la variable s de tipo Serie.
    return new SerieDTO(s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(), s.getGenero(),
    s.getActores(), s.getSinopsis());
    Aquí se crea un nuevo objeto SerieDTO utilizando los datos de la Serie encontrada (s).
    Los valores de las propiedades de la Serie (título, total de temporadas, evaluación, póster,
    género, actores, sinopsis) se pasan como argumentos al constructor de SerieDTO.
    Este objeto SerieDTO es el que se retorna como resultado del método.
    } else {
    Si el Optional serie no contiene un valor (es decir, no se encontró una Serie con el id
    proporcionado), se ejecuta este bloque de código.
    return null;
    Si no se encontró una Serie, se retorna null como resultado del método.
    En resumen, este método busca una Serie por su id en el repositorio, y si la encuentra,
    crea y retorna un objeto SerieDTO con los datos de la Serie. Si no se encuentra la Serie, retorna null.
    * */
    public SerieDTO obtenerPorId(long id) {
        Optional<Serie>  serie = repository.findById(id);
        if(serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(s.getId(),s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(),
                    s.getPoster(),s.getGenero() ,s.getActores(), s.getSinopsis());
        } else {
            return null;
        }
    }

    /*Modulo 4.1.2 */
    public List<EpisodioDTO> obtenerTodasLasTemporadas(Long id) {
        Optional<Serie>  serie = repository.findById(id);
        if(serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    /*Modulo 4.2
    * se crea el metodo .obtenerTemporadaPorNumeros() en SerieRepository*/
    public List<EpisodioDTO> obtenerTemporadasPorNumero(Long id, Long numeroTemporada) {
        return repository.obtenerTemporadaPorNumeros(id, numeroTemporada).stream()
                .map(e ->new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio()))
                .collect(Collectors.toList());
    }


    /*Modelo 4.3
    * IMPORTNATE RECORDAR QUE CATEGORIAS  EN NUESTRO CODIGO NO ESTA MAPEADO
    * COMO UN STRING SINO  COMO UN DATO DE TIPO ENUM.
    * PERO  en nuestro enum ya tenemos el metodo para convertir de Dato Enum a String
    *  */
    public List<SerieDTO> obtenerSeriesPorCategoria(String nombreGenero) {
        Categoria categoria = Categoria.fromEspanol(nombreGenero);
        return convierteDatos(repository.findByGenero(categoria));
    }
}
