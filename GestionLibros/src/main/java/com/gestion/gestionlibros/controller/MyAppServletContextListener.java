package com.gestion.gestionlibros.controller;

import com.gestion.gestionlibros.model.data.DBConnector;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MyAppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            DBConnector.getInstance().connection("LibrosBD", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DBConnector.getInstance().closeConnection();
    }
}



