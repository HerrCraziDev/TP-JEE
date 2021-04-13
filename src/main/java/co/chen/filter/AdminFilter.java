package co.chen.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    
    private static final long serialVersionUID = 1L;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override 
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /* Si la variable de session n’existe pas l’admin n’est pas connecté */
        if ( session.getAttribute( "admin" ) == null ) {
            String URL = "/adminLogin";
            System.out.println("[Filter] [AdminFilter] INFO: Redirecting to " + request.getContextPath() + URL);
            response.sendRedirect(request.getContextPath() + URL);
        } else {
            /* Affichage de la page restreinte */
            chain.doFilter( request, response );
        }
    }
}
