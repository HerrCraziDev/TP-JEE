package co.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; 


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
        String name, surname, city, postcode, address, mail, phone, passwd, passwd_confirm;
        Boolean dirty = false;

        request.setCharacterEncoding("UTF-8");

        if ( (name = request.getParameter("name")).isEmpty() ) {
            dirty = true;
            System.out.println("[Servlet] [CreationClient] POST /createClient WARN : Missing parameter : name");
        }
        if ( (surname = request.getParameter("surname")).isEmpty() ) {
            dirty = true;
            System.out.println("[Servlet] [CreationClient] POST /createClient WARN : Missing parameter : surname");
        }
        if ( (city = request.getParameter("city")).isEmpty() ) {
            dirty = true;
            System.out.println("[Servlet] [CreationClient] POST /createClient WARN : Missing parameter : city");
        }
        if ( (postcode = request.getParameter("postcode")).isEmpty() ) {
            dirty = true;
            System.out.println("[Servlet] [CreationClient] POST /createClient WARN : Missing parameter : postcode");
        }
        if ( (address = request.getParameter("address")).isEmpty() ) {
            dirty = true;
            System.out.println("[Servlet] [CreationClient] POST /createClient WARN : Missing parameter : address");
        }
        if ( (phone = request.getParameter("phone")).isEmpty() ) {
            dirty = true;
            System.out.println("[Servlet] [CreationClient] POST /createClient WARN : Missing parameter : phone");
        }
        if ( (mail = request.getParameter("mail")).isEmpty() ) {
            dirty = true;
            System.out.println("[Servlet] [CreationClient] POST /createClient WARN : Missing parameter : mail");
        }
        if ( (passwd = request.getParameter("passwd")).isEmpty() ) {
            dirty = true;
            System.out.println("[Servlet] [CreationClient] POST /createClient WARN : Missing parameter : passwd");
        }
        if ( (passwd_confirm = request.getParameter("passwdconfirm")).isEmpty() ) {
            dirty = true;
            System.out.println("[Servlet] [CreationClient] POST /createClient WARN : Missing parameter : passwdconfirm");
        }

        if (dirty) {
            System.out.println("[Servlet] [CreationClient] POST /createClient --> Forwarding to " + jsp_createClient);
            request.setAttribute("error", true);
            this.getServletContext().getRequestDispatcher(jsp_createClient).forward(request, response);
        } else {
            request.setAttribute("error", false);
            System.out.println("[Servlet] [CreationClient] POST /createClient INFO : All OK, forwarding");
            System.out.println("[Servlet] [CreationClient] POST /createClient --> Forwarding to " + jsp_displayClient);
            this.getServletContext().getRequestDispatcher(jsp_displayClient).forward(request, response);
        }
    }
    //
}