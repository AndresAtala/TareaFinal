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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "listaLibroServlet", value = "/mostrarLibros")
public class ListaLibroServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("LibrosBD");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Libro> libros = obtenerLibros();
            List<String> categorias = obtenerCategorias();

            req.setAttribute("libros", libros);
            req.getSession().setAttribute("categorias", categorias);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher respuesta = req.getRequestDispatcher("/mostrarLibros.jsp");
        respuesta.forward(req, resp);
    }

    private List<Libro> obtenerLibros() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("LibrosBD");
        LibroDAO libroDAO = new LibroDAO();
        return libroDAO.obtenerLibros(query, "nombre", null);
    }

    private List<String> obtenerCategorias() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("LibrosBD");
        LibroDAO libroDAO = new LibroDAO();
        return libroDAO.obtenerCategorias(query);
    }
}
