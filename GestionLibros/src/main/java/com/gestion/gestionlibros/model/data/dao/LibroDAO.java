package com.gestion.gestionlibros.model.data.dao;

import com.gestion.gestionlibros.model.Libro;
import org.jooq.*;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.INTEGER;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class LibroDAO {

    private static final Table<?> LIBRO_TABLE = table(name("Libro"));
    private static final Field<String> NOMBRE_FIELD = field(name("nombre"), VARCHAR);
    private static final Field<String> CATEGORIA_FIELD = field(name("categoria"), VARCHAR);
    private static final Field<Integer> ANO_FIELD = field(name("ano"), INTEGER);

    public static void agregarLibro(DSLContext query, Libro libro) {
        query.insertInto(LIBRO_TABLE, NOMBRE_FIELD, CATEGORIA_FIELD, ANO_FIELD)
                .values(libro.getNombre(), libro.getCategoria(), libro.getAno())
                .execute();
    }

    public void modificarLibro(DSLContext query, String nombre, String columnaTabla, Object dato) {
        query.update(LIBRO_TABLE)
                .set(field(name(columnaTabla)), dato)
                .where(NOMBRE_FIELD.eq(nombre))
                .execute();
    }

    public List<Libro> obtenerLibros(DSLContext query, String columnaTabla, Object dato) {
        Result<org.jooq.Record> resultados = query.select()
                .from(LIBRO_TABLE)
                .where(field(name(columnaTabla)).eq(dato))
                .fetch();

        return obtenerListaLibros(resultados);
    }

    public List<String> obtenerCategorias(DSLContext query) {
        return query.selectDistinct(CATEGORIA_FIELD).from(LIBRO_TABLE).fetch(CATEGORIA_FIELD);
    }

    public void eliminarLibro(DSLContext query, String nombre) {
        query.delete(LIBRO_TABLE)
                .where(NOMBRE_FIELD.eq(nombre))
                .execute();
    }

    private List<Libro> obtenerListaLibros(Result<org.jooq.Record> resultados) {
        List<Libro> libros = new ArrayList<>();
        for (org.jooq.Record record : resultados) {
            String nombre = record.get(NOMBRE_FIELD, String.class);
            String categoria = record.get(CATEGORIA_FIELD, String.class);

            // Verifica si el valor de ANO_FIELD es nulo antes de invocar intValue()
            Integer ano = record.get(ANO_FIELD, Integer.class);
            int anoValue = (ano != null) ? ano.intValue() : 0;

            libros.add(new Libro(nombre, categoria, anoValue));
        }
        return libros;
    }

    public void agregarCategoria(DSLContext query, String nuevaCategoria) {
        // Puedes asignar un nombre específico para la nueva categoría
        String nombreCategoria = "Categoria" + nuevaCategoria;

        // Inserta la nueva categoría en la tabla Libro con un nombre específico
        query.insertInto(LIBRO_TABLE, NOMBRE_FIELD, CATEGORIA_FIELD)
                .values(nombreCategoria, nuevaCategoria)
                .execute();
    }
    public List<Libro> obtenerTodosLosLibros(DSLContext query) {
        Result<org.jooq.Record> resultados = query.select()
                .from(LIBRO_TABLE)
                .fetch();

        return obtenerListaLibros(resultados);
    }

}
