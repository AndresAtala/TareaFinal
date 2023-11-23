package com.gestion.gestionlibros.controller;
import com.gestion.gestionlibros.model.Libro;
import com.gestion.gestionlibros.model.data.DBGenerator;
import com.gestion.gestionlibros.model.data.dao.LibroDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jooq.DSLContext;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "mostrarTodosLibrosServlet", value = "/mostrarTodosLibrosServlet")
public class MostrarTodosLibrosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Libro> libros = obtenerTodosLosLibros();
            List<String> categorias = obtenerCategorias();

            req.setAttribute("libros", libros);
            req.getSession().setAttribute("categorias", categorias);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher respuesta = req.getRequestDispatcher("/mostrarTodosLibros.jsp");
        respuesta.forward(req, resp);
    }

    private List<Libro> obtenerTodosLosLibros() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("LibrosBD");
        LibroDAO libroDAO = new LibroDAO();
        return libroDAO.obtenerTodosLosLibros(query);
    }

    private List<String> obtenerCategorias() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("LibrosBD");
        LibroDAO libroDAO = new LibroDAO();
        return libroDAO.obtenerCategorias(query);
    }
}

