package net.therap.filter;

import net.therap.domain.User;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoginFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest) servletRequest).getRequestURI();

        if (path.contains("loginform") || path.contains("registration")) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
            User user = (User) session.getAttribute("User");
            if (user == null) {
                ((HttpServletResponse) servletResponse).sendRedirect("loginform.html");
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {
    }
}
