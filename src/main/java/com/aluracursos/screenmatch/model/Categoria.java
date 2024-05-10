
package com.aluracursos.screenmatch.model;

public enum Categoria {
    ACCION("Action", "Acción"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime", "Crimen");
    private String categoriaOmdb;
    private String categoriaEspanol;
    Categoria(String categoriaOmdb, String categoriaEspanol){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEspanol = categoriaEspanol;

    }


    /*
    este código en español:
    Este es un método estático llamado fromString() que pertenece a una clase Categoria. El propósito de este método es convertir un valor de tipo String a un objeto de tipo Categoria.
    Aquí está el funcionamiento paso a paso:
	1. Se recorre el arreglo de valores de la enumeración Categoria.values().

	2. Para cada valor de Categoria, se compara el campo categoriaOmdb (que presumiblemente
	 es otro atributo de la enumeración) con el text pasado como parámetro, ignorando mayúsculas
	 y minúsculas.

	3. Si se encuentra una coincidencia, se devuelve el objeto Categoria correspondiente.

	4. Si no se encuentra ninguna coincidencia, se lanza una excepción IllegalArgumentException
	 con un mensaje indicando que no se encontró ninguna categoría para el texto proporcionado.

    Este método es útil cuando se necesita convertir un valor de texto (como puede ser el recibido de
    una API o una entrada del usuario) a un objeto de tipo Categoria, que probablemente sea una enumeración
    que representa las categorías o géneros disponibles en el sistema.
     */
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

    /*
      este código en español:
    Este es un método estático llamado fromEspanol() que pertenece a una clase Categoria. El
     propósito de este método es convertir un valor de tipo String (que representa el nombre
     de una categoría en español) a un objeto de tipo Categoria.
    Aquí está el funcionamiento paso a paso:

	1. Se recorre el arreglo de valores de la enumeración Categoria.values().

	2. Para cada valor de Categoria, se compara el campo categoriaEspanol (que presumiblemente
	 es otro atributo de la enumeración que contiene el nombre de la categoría en español) con
	 el text pasado como parámetro, ignorando mayúsculas y minúsculas.

	3. Si se encuentra una coincidencia, se devuelve el objeto Categoria correspondiente.

	4. Si no se encuentra ninguna coincidencia, se lanza una excepción IllegalArgumentException
	 con un mensaje indicando que no se encontró ninguna categoría para el texto proporcionado.

    Este método es útil cuando se necesita convertir un valor de texto (como puede ser el recibido
    de una entrada del usuario o de una API) que representa el nombre de una categoría en español
    a un objeto de tipo Categoria, que probablemente sea una enumeración que representa las categorías
    o géneros disponibles en el sistema. Por ejemplo, si el usuario ingresa "Comedia"
    como el nombre de la categoría, este método buscaría en la enumeración Categoria el objeto que tenga el valor "Comedia" en su campo categoriaEspanol y lo devolvería. Si no se encuentra ninguna coincidencia, se lanzaría una excepción indicando que no se encontró la categoría.

    * */
    public static Categoria fromEspanol(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaEspanol.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
}
