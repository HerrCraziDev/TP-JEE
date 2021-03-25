package co.chen.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/displayClient")
public class DisplayClient extends HttpServlet {

    private static final long serialVersionUID = -8627270182927337176L;
    public static final String TAR_GET = "/WEB-INF/jsp/displayClient.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("[Servlet] [DisplayClient] GET /displayClient --> Forwarding to " + TAR_GET);
        this.getServletContext().getRequestDispatcher(TAR_GET).forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("[Servlet] [DisplayClient] GET /displayClient --> Forwarding to " + TAR_GET);
        this.getServletContext().getRequestDispatcher(TAR_GET).forward(req, res);
    }
    //
}