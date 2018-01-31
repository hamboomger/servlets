package pl.epoint.servlets.servlets;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.epoint.servlets.util.ServletUtils.getRequiredParam;

@Log4j2
public class LoginServlet extends HttpServlet {

    private volatile int usersIdCounter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(performLogin(req)) {
            req.getSession().setAttribute("userId", usersIdCounter++);
            resp.sendRedirect("/products/");
        } else {
            req.setAttribute("error", "Login or password is invalid");
            resp.sendRedirect("/products/login");
        }
    }

    private boolean performLogin(HttpServletRequest httpRequest) {
        String login = getRequiredParam("login", httpRequest);
        String password = getRequiredParam("password", httpRequest);
        log.info("Login: {}, password: {}", login, password);

        /* return login.equals("jack") && password.equals("sparrow");
           Stałe z lewej strony - de facto hasło możnaby umieścić w kongiruacji
         */
        return "jack".equals(login) && "sparrow".equals(password);
    }

}
