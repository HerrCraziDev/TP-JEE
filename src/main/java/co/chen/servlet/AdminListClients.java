package co.chen.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import co.chen.bean.Client;
import co.chen.dao.ClientDAO;
import co.chen.dao.factory.DAOFactory;
import co.chen.service.ClientManager;

@WebServlet("/admin/clients")
public class AdminListClients extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String jsp_displayClients = "/WEB-INF/jsp/admin/displayClients.jsp";
    private ClientDAO clientDAO;

    
    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = (DAOFactory) getServletContext().getAttribute("daoFactory");
        this.clientDAO = daoFactory.getClientDAO();
    }
    
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        ClientManager cm = new ClientManager();
        List<Client> clients = cm.fetchClients(clientDAO);

        System.out.println(clients);
        request.setAttribute("clients", clients);

        System.out.println("[Servlet] [DisplayClient] GET /displayClient --> Forwarding to " + jsp_displayClients);
        this.getServletContext().getRequestDispatcher(jsp_displayClients).forward(request, response);
    }
}
