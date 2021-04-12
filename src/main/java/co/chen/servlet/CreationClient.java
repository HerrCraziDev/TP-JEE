package co.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import co.chen.bean.Client;
import co.chen.form.CreationClientForm;
import co.chen.service.ClientManager; 


@WebServlet("/createClient")
public class CreationClient extends HttpServlet {

    private static final long serialVersionUID = -8627270182927337176L;
    public static final String jsp_createClient = "/WEB-INF/jsp/createClient.jsp";
    public static final String jsp_displayClient = "/WEB-INF/jsp/displayClient.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        System.out.println("[Servlet] [CreationClient] GET /createClient --> Forwarding to " + jsp_createClient);
        this.getServletContext().getRequestDispatcher(jsp_createClient).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String target;
        CreationClientForm form = new CreationClientForm();
        Client client = new Client();

        request.setCharacterEncoding("UTF-8");

        // Validate form
        client = form.validate(request);
        request.setAttribute("form", form);
        
        // Routing and creation if no errors
        if ( form.hasErrors() ) {
            // There are errors
            request.setAttribute("error", true);
            target = jsp_createClient;
        } else {
            // There is no error, safe to continue
            ClientManager cm = new ClientManager();
            cm.createClient(client);
            
            request.setAttribute("error", false);
            target = jsp_displayClient;
            System.out.println("[Servlet] [CreationClient] POST /createClient INFO : All OK, forwarding");
        }

        request.setAttribute("client", client);

        System.out.println("[Servlet] [CreationClient] POST /createClient --> Forwarding to " + target);
        this.getServletContext().getRequestDispatcher(target).forward(request, response);
    }
    //
}