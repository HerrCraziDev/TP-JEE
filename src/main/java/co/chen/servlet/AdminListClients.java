package co.chen.servlet;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import co.chen.bean.Client;
import co.chen.service.ClientManager;

@WebServlet("/admin/clients")
public class AdminListClients extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) {
        ClientManager cm = new ClientManager();
        List<Client> clients = cm.fetchClients();

        System.out.println(clients);
    }
}
