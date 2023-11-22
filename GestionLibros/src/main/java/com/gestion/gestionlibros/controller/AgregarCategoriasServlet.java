package com.gestion.gestionlibros.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.gestion.gestionlibros.model.Libro;
import com.gestion.gestionlibros.model.data.DBGenerator;
import com.gestion.gestionlibros.model.data.dao.LibroDAO;
import org.jooq.DSLContext;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "agregarCategoriasServlet", value = "/AgregarCategoriasServlet")
public class AgregarCategoriasServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("LibrosBD");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nuevaCategoria = req.getParameter("categoria");

        try {
            DSLContext query = DBGenerator.conectarBD("LibrosBD");
            LibroDAO libroDAO = new LibroDAO();
            List<Libro> librosConCategoria = libroDAO.obtenerLibros(query, "categoria", nuevaCategoria);

            if (librosConCategoria.isEmpty()) {
                libroDAO.agregarCategoria(query, nuevaCategoria);

                // Obtener las categorías actualizadas y establecerlas en la sesión
                List<String> categorias = libroDAO.obtenerCategorias(query);
                req.getSession().setAttribute("categorias", categorias);

                req.setAttribute("mensaje", "Categoría agregada exitosamente");
            } else {
                req.setAttribute("mensaje", "La categoría ya existe");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Redirigir a la página de agregarCategoriaLibros.jsp
        RequestDispatcher respuesta = req.getRequestDispatcher("/CategoriaAgregadaExito.jsp");
        respuesta.forward(req, resp);
    }
}
