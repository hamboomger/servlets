package pl.epoint.servlets.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.epoint.servlets.util.ServletUtils.getRequiredParam;

public class LoginFilter implements Filter {

    private static final String LOGIN_ACTION = "login";

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        if(httpRequest.getSession().getAttribute("userId") != null) {
            chain.doFilter(request, response);
            return;
        }

        String actionParam = getRequiredParam("action", httpRequest);

        switch (actionParam) {
            case LOGIN_ACTION:
                boolean success = performLogin(httpRequest);
                if(success) {
                    chain.doFilter(request, response);
                } else {
                    httpRequest.setAttribute("error", "Login or password is invalid");
                    httpResponse.sendRedirect("/products/login");
                }
        }
    }

    private boolean performLogin(HttpServletRequest httpRequest) {
        String login = getRequiredParam("login", httpRequest);
        String password = getRequiredParam("password", httpRequest);
        return login.equals("jack") && password.equals("sparrow");
    }

    @Override
    public void destroy() {}
}
