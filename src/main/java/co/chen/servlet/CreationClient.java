package co.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; 


@WebServlet("/createClient")
public class CreationClient extends HttpServlet {

    private static final long serialVersionUID = -8627270182927337176L;
    public static final String TAR_GET = "/WEB-INF/jsp/createClient.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
        System.out.println("[Servlet] [CreationClient] GET /createClient --> Forwarding to " + TAR_GET);
        this.getServletContext().getRequestDispatcher(TAR_GET).forward(req, res);
    }
    //
}