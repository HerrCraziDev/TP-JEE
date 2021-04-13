package co.chen.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/images/*")
public class Download extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Download->Start Process");
        
        int BUFFER_SIZE = 1024*1024*8;
        String path ="/home/herrcrazi/dev/uploads/";

        /* Récupération du path du fichier demandé au sein de l'URL de la requête */
        String wantedFile = request.getPathInfo();
        /* Vérifie qu'un nom de fichier a bien été fourni*/
        if ( wantedFile == null || "/".equals( wantedFile ) ) {
            /* Envoie une erreur 404 si le fichier n'est pas défini */
            response.sendError( HttpServletResponse.SC_NOT_FOUND );
            return;
        }

        /* Décode le nom de fichier récupéré */
        wantedFile = URLDecoder.decode( wantedFile, "UTF-8" );
        File file = new File( path, wantedFile );
        System.out.println("Nom Fichier : "+wantedFile);

        /* Vérifie que le fichier existe bien */
        if ( !file.exists() ) {
            // file does not exists, send default
            file = new File(path, "default.png");

            if ( !file.exists() ) {
                response.sendError( HttpServletResponse.SC_NOT_FOUND );
                return;
            }
        }

        /* Récupère le type du fichier */
        String type = request.getServletContext().getMimeType( file.getName() );

        /* Si le type de fichier est inconnu, alors on initialise un type par défaut */
        if ( type == null ) {
            type = "application/octet-stream";
        }

        /* Initialise la réponse HTTP */
        response.reset();
        response.setBufferSize( BUFFER_SIZE );
        response.setContentType( type );
        response.setHeader( "Content-Length", String.valueOf( file.length() ) );
        response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );
        
        /* Prépare les flux */
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        
        try {
            in = new BufferedInputStream( new FileInputStream( file ), BUFFER_SIZE );
            out = new BufferedOutputStream( response.getOutputStream(), BUFFER_SIZE );
            
            /* Lit le fichier et écrit son contenu dans la réponse HTTP */
            byte[] buffer = new byte[BUFFER_SIZE];
            int length;

            while ( ( length = in.read( buffer ) ) > 0 ) {
                out.write( buffer, 0, length );
            }
        } finally {
            try {
                out.close();
            } catch ( IOException ignore ) {}
            try {
                in.close();
            } catch ( IOException ignore ) {}
        }
        System.out.println("Download->End Process");
    }
}
