package co.chen.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import co.chen.bean.Client;
import co.chen.dao.ClientDAO;
import co.chen.dao.factory.DAOFactory;
import co.chen.form.CreationClientForm;
import co.chen.service.ClientManager; 


@WebServlet("/createClient")
@MultipartConfig(location = "/home/herrcrazi/dev/uploads", fileSizeThreshold = 0, maxFileSize = 1024 * 1024 * 8, maxRequestSize = 1024* 1024 * 8)
public class CreationClient extends HttpServlet {

    private static final long serialVersionUID = -8627270182927337176L;
    private ClientDAO clientDAO;
    public static final String jsp_createClient = "/WEB-INF/jsp/createClient.jsp";
    public static final String jsp_displayClient = "/WEB-INF/jsp/displayClient.jsp";


    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = (DAOFactory) getServletContext().getAttribute("daoFactory");
        this.clientDAO = daoFactory.getClientDAO();
    }


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
            cm.createClient(client, clientDAO);
            
            String path = "/home/herrcrazi/dev/uploads/";
            if ( form.shouldCreateFile() ) saveFile(request, "image", path, client.getClientId() + ".png");

            request.setAttribute("error", false);
            target = jsp_displayClient;
            System.out.println("[Servlet] [CreationClient] POST /createClient INFO : All OK, forwarding");
        }

        request.setAttribute("client", client);

        System.out.println("[Servlet] [CreationClient] POST /createClient --> Forwarding to " + target);
        this.getServletContext().getRequestDispatcher(target).forward(request, response);
    }

    private void saveFile( HttpServletRequest request, String field, String path, String filename ) throws IOException {
        int BUFFER_SIZE = 1024*1024*8;

        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            Part part = request.getPart( field );
            
            in = new BufferedInputStream( part.getInputStream(), BUFFER_SIZE );
            out = new BufferedOutputStream( new FileOutputStream( new File( path + filename ) ),BUFFER_SIZE );
            
            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ( ( length = in.read( buffer ) ) > 0 ) {
                out.write( buffer, 0, length );
            }
        } catch( Exception e ) {
            System.err.println("[Servlet] [CreationClient] POST Error : " + e);
        } finally {
            try {
                out.close();
            } catch ( IOException e ) {
                System.out.println("Error :"+e);
            }
            try {
                in.close();
            } catch ( IOException e ) {
                System.out.println("Error :"+e);
            }
        }
    }
}