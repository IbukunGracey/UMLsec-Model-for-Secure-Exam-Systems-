/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectFilters;

import Bean.CheckForWebCam;
import java.io.IOException;
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
public class RegistrationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    HttpSession session = request.getSession(false);
    String restrictedURI = request.getContextPath() + "/secured/examtaker-reg.xhtml";
        System.out.println("this is the path " + request.getContextPath());
        CheckForWebCam ch = new CheckForWebCam();

        
    //boolean loggedIn = session != null && session.getAttribute("user") != null;
    if (restrictedURI.equals("/OnlineExam/secured/examtaker-reg.xhtml") && CheckForWebCam.getFf().equals("")) {
       response.sendRedirect("/OnlineExam/index.xhtml");
    } else {
        chain.doFilter(request, response);
    }
    }

    @Override
    public void destroy() {
    }
    
}
