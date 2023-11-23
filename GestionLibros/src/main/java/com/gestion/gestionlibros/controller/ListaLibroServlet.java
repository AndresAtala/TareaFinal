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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("buscar".equals(action)) {
            try {
                String criterio = req.getParameter("criterio");
                String valorBusqueda = req.getParameter("valorBusqueda");

                List<Libro> libros = buscarLibros(criterio, valorBusqueda);
                List<String> categorias = obtenerCategorias();

                req.setAttribute("libros", libros);
                req.getSession().setAttribute("categorias", categorias);

                RequestDispatcher respuesta = req.getRequestDispatcher("/mostrarLibros.jsp");
                respuesta.forward(req, resp);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if ("mostrarTodos".equals(action)) {
            resp.sendRedirect("mostrarTodosLibros");

        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    private List<Libro> buscarLibros(String criterio, String valorBusqueda) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("LibrosBD");
        LibroDAO libroDAO = new LibroDAO();

        switch (criterio) {
            case "nombre":
                return libroDAO.obtenerLibros(query, "nombre", valorBusqueda);
            case "categoria":
                return libroDAO.obtenerLibros(query, "categoria", valorBusqueda);
            case "ano":
                int ano = Integer.parseInt(valorBusqueda);
                return libroDAO.obtenerLibros(query, "ano", ano);
            default:
                return new ArrayList<>();
        }
    }

    private List<String> obtenerCategorias() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("LibrosBD");
        LibroDAO libroDAO = new LibroDAO();
        return libroDAO.obtenerCategorias(query);
    }
}