package co.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import co.chen.bean.Admin;
import co.chen.dao.AdminDAO;
import co.chen.dao.factory.DAOFactory;
import co.chen.service.AdminManager;


@WebServlet("/adminLogin")
public class AdminLogin extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    private AdminDAO adminDAO;
    private static final String jsp_adminLogin = "/WEB-INF/jsp/admin/adminLogin.jsp";
    private static final String jsp_displayClients = "/WEB-INF/jsp/admin/displayClients.jsp";


    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = (DAOFactory) getServletContext().getAttribute("daoFactory");
        this.adminDAO = daoFactory.getAdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[Servlet] [AdminLogin] GET /adminLogin --> Forwarding to " + jsp_adminLogin);
        
        request.getSession().invalidate();
        this.getServletContext().getRequestDispatcher(jsp_adminLogin).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        AdminManager am = new AdminManager();
        Admin admin = new Admin();

        String identifier = request.getParameter("identifier"), password = request.getParameter("password");

        admin = am.validateAdminAccess(adminDAO, identifier, password);

        if ( admin == null ) {
            request.setAttribute("errormessage", "Identifiant ou mot de passe invalide");
            request.setAttribute("error", true);

            System.out.println("[Servlet] [AdminLogin] GET /adminLogin --> Forwarding to " + jsp_adminLogin);
            this.getServletContext().getRequestDispatcher(jsp_adminLogin).forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);

            Cookie chocolat = new Cookie("identifier", identifier);
            chocolat.setMaxAge(3600*24*31);
            response.addCookie(chocolat);

            response.sendRedirect(request.getContextPath() + "/admin/clients");
            System.out.println("[Servlet] [AdminLogin] GET /adminLogin --> Redirecting to /admin/clients [OK]");
        }
    }
}
