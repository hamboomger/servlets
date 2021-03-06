package pl.epoint.servlets.servlets;

import lombok.extern.log4j.Log4j2;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import pl.epoint.servlets.dao.ProductManager;
import pl.epoint.servlets.model.Product;

import static pl.epoint.servlets.util.ServletParameters.ACTION;
import static pl.epoint.servlets.util.ServletUtils.getRequiredBigDecimalParam;
import static pl.epoint.servlets.util.ServletUtils.getRequiredIntegerParam;
import static pl.epoint.servlets.util.ServletUtils.getRequiredParam;

@Log4j2
public class ProductEditServlet extends HttpServlet {

    private static final String SAVE_PRODUCT_ACTION = "save_product";

    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_PRICE = "productPrice";
    private static final String PRODUCT_ID = "productId";

    @EJB(beanName = "ProductDatabaseManagerImpl")
    private ProductManager productManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer productId = getRequiredIntegerParam(PRODUCT_ID, req);

        addProductToRequestAttributes(req, productId);
        req.getRequestDispatcher("/product-edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = getRequiredParam(ACTION, req);

        //dla jednej akcji lepiej dać ifa zamiast switcha.
        switch (action) {
            case SAVE_PRODUCT_ACTION:
                saveProduct(req);
                resp.sendRedirect("/products/list/");
                break;
            default:
                resp.sendError(418, "Unknown action: " + action);
                break;
        }
    }

    private void addProductToRequestAttributes(HttpServletRequest req, Integer productId) {
        Product product = productManager.getProductByPK(productId);
        if (product == null)
            throw new IllegalArgumentException(String.format("There is no product with id '%s'", productId));

        req.setAttribute("product", product);
    }

    private void saveProduct(HttpServletRequest req) {
        Integer id = getRequiredIntegerParam(PRODUCT_ID, req);
        String name = getRequiredParam(PRODUCT_NAME, req);
        BigDecimal price = getRequiredBigDecimalParam(PRODUCT_PRICE, req);

        Product updatedProduct = new Product(name, price);
        updatedProduct.setId(id);

        productManager.updateProduct(updatedProduct);

        log.info("Product '{}' updated", name);
    }

}