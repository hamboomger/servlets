package pl.epoint.servlets.servlets;

import lombok.extern.log4j.Log4j2;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import pl.epoint.servlets.dao.ProductManager;

@Log4j2
public class ProductListServlet extends HttpServlet {

    @EJB(beanName = "ProductDatabaseManagerImpl")
    private ProductManager productManager;

    private volatile int visitsCounter; //teoretycznie tu powinno być volatile

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productManager.getProductsList());
        req.getRequestDispatcher("/list.jsp").forward(req, resp);

        log.info("Visits counter[List Servlet]: {}", ++visitsCounter);
    }

}