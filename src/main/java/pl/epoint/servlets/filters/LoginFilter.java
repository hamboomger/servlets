package pl.epoint.servlets.filters;

import org.jetbrains.annotations.NotNull;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import pl.epoint.servlets.util.ServletUtils;

import static pl.epoint.servlets.util.ServletUtils.getRequiredParam;

public class LoginFilter implements Filter {

    public static final String LOGIN_ACTION = "login";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = castToHttpRequest(request);
        HttpServletResponse httpResponse = castToHttpResponse(response);
        HttpSession session = httpRequest.getSession();

        String actionParam = getRequiredParam("action", httpRequest, httpResponse);
        if(actionParam == null) return;

        switch (actionParam) {
            case LOGIN_ACTION:
                String login = getRequiredParam("login", httpRequest, httpResponse);
                if(login == null) return;

                String password = getRequiredParam("password", httpRequest, httpResponse);
                if(password == null) return;


        }
    }

    @NotNull
    private HttpServletRequest castToHttpRequest(ServletRequest request) {
        if(!(request instanceof HttpServletRequest))
            throw new IllegalArgumentException("Only HttpServletRequest's are supported");

        return (HttpServletRequest)request;
    }

    @NotNull
    private HttpServletResponse castToHttpResponse(ServletResponse request) {
        if(!(request instanceof HttpServletResponse))
            throw new IllegalArgumentException("Only HttpServletResponses are supported");

        return (HttpServletResponse) request;
    }

    @Override
    public void destroy() {

    }
}
