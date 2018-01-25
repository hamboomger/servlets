package pl.epoint.servlets.servlets;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Nullable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import pl.epoint.servlets.dao.ProductManager;
import pl.epoint.servlets.dao.ProductMemoryManagerImpl;
import pl.epoint.servlets.model.Product;

import static pl.epoint.servlets.util.ServletUtils.*;

@Log4j2
public class ProductAddServlet extends HttpServlet {

    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_PRICE = "productPrice";

    private ProductManager productManager = ProductMemoryManagerImpl.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/product-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Product newProduct = assembleProductFromRequest(req, resp);
        productManager.insertProduct(newProduct);

        log.info("New product added: " + newProduct.getName());

        resp.sendRedirect("/products/list");
    }

    private Product assembleProductFromRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = getRequiredParam(PRODUCT_NAME, req, resp);
        BigDecimal price = getRequiredBigDecimalParam(PRODUCT_PRICE, req, resp);
        return new Product(name, price);
    }
}
