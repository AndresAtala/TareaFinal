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

@WebServlet(name = "registroLibroServlet", value = "/registroLibro")
public class RegistroLibroServlet extends HttpServlet {

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
        List<String> categorias = (List<String>) req.getSession().getAttribute("categorias");
        req.setAttribute("categorias", categorias);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroErroneo.jsp");

        try {
            String nombre = req.getParameter("nombre");
            String categoria = req.getParameter("categoria");
            int ano = Integer.parseInt(req.getParameter("ano"));
            Libro libro = new Libro(nombre, categoria, ano);

            if (agregarLibro(libro)) {
                req.setAttribute("libro", libro);
                respuesta = req.getRequestDispatcher("/libroAgregadoExito.jsp");
            } else {
                // Agregar la lista de categorías al atributo "mensaje"
                List<String> categorias = obtenerCategoriasDesdeSesion(req);
                req.setAttribute("categorias", categorias);

                req.setAttribute("mensaje", "El libro ya existe");
            }
        } catch (NumberFormatException | ClassNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("mensaje", "Error al procesar la solicitud");
        }

        respuesta.forward(req, resp);
    }
    private List<String> obtenerCategoriasDesdeSesion(HttpServletRequest req) {
        return (List<String>) req.getSession().getAttribute("categorias");
    }


    private boolean agregarLibro(Libro libro) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("LibrosBD");
        LibroDAO libroDAO = new LibroDAO();
        List<Libro> libros = libroDAO.obtenerLibros(query, "nombre", libro.getNombre());
        if (libros.size() != 0) {
            return false;
        } else {
            libroDAO.agregarLibro(query, libro);
            return true;
        }
    }
}
