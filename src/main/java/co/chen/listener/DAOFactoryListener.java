package co.chen.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

import co.chen.dao.factory.DAOFactory;

@WebListener
public class DAOFactoryListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        /* Instanciation de notre DAOFactory */
        DAOFactory daoFactory = DAOFactory.getInstance();
        /* Enregistrement dans un attribut ayant pour portée toute l'application */
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("daoFactory", daoFactory);
    }
}
