package pl.epoint.servlets.filters;

import lombok.extern.log4j.Log4j2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
public class CounterFilter implements Filter {

    private static final String COUNTER = "counter";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();

        updateSessionCounter(session);
        updateContextCounter(session);

        chain.doFilter(request, response);
    }

    private void updateContextCounter(HttpSession session) {
        ServletContext context = session.getServletContext();
        Integer counter = (Integer) context.getAttribute(COUNTER);
        if (counter == null) {
            counter = 0;
        }
        context.setAttribute(COUNTER, ++counter);
        log.info("Visits counter[Context]: {}", counter);
    }

    private void updateSessionCounter(HttpSession session) {
        Integer counter = (Integer) session.getAttribute(COUNTER);
        if (counter == null) {
            counter = 0;
        }
        session.setAttribute(COUNTER, ++counter);
        log.info("Visits counter[Session]: {}", counter);
    }

    @Override
    public void destroy() {
    }
}