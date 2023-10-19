/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectFilters;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tunde
 */
public class LoginFilter implements Filter {
    
    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    HttpSession session = request.getSession(false);
    String loginURI = request.getContextPath() + "/index.xhtml";
        System.out.println("this is the path " + request.getContextPath());

    boolean loggedIn = session != null && session.getAttribute("userId") != null;
    boolean loginRequest = request.getRequestURI().equals(loginURI);
    boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

    if (loggedIn || loginRequest || resourceRequest) {
        chain.doFilter(request, response);
    } else {
        response.sendRedirect(loginURI);
    } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
    }
}
