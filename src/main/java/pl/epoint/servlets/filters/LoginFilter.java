package pl.epoint.servlets.filters;

import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.epoint.servlets.util.ServletParameters.ACTION;

public class LoginFilter implements Filter {

    private static final String LOGIN_PAGE = "/products/login/";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (requestHasParameter(httpRequest, ACTION, "logout")) {
            httpRequest.getSession().setAttribute("userId", null);
            httpResponse.sendRedirect(LOGIN_PAGE);

        } else if (sessionContainsUserId(httpRequest)) {
            chain.doFilter(request, response);

        } else if (LOGIN_PAGE.equals(httpRequest.getRequestURI())) {
            chain.doFilter(request, response);

        } else {
            httpResponse.sendRedirect(LOGIN_PAGE);
        }
    }

    private boolean requestHasParameter(HttpServletRequest req, String attributeName, String attributeValue) {
        Object value = req.getParameter(attributeName);
        return attributeValue.equals(value); // wtedy nie musisz sprawdzac czy value jest null, jeszcze lepiej
        // sprawdzać tak:  Objects.equals(value, attributeName) - wtedy na pewno się uchronisz przed npe.
    }

    private boolean sessionContainsUserId(HttpServletRequest httpRequest) {
        return httpRequest.getSession().getAttribute("userId") != null;
    }

    @Override
    public void destroy() {
    }
}
