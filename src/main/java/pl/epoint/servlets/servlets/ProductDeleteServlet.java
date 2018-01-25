package pl.epoint.servlets.servlets;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import pl.epoint.servlets.dao.ProductManager;
import pl.epoint.servlets.dao.ProductMemoryManagerImpl;
import pl.epoint.servlets.model.Product;

import static pl.epoint.servlets.util.ServletUtils.getRequiredIntegerParam;

@Log4j2
public class ProductDeleteServlet extends HttpServlet {

    private static final String PRODUCT_ID = "productId";

    private ProductManager productManager = ProductMemoryManagerImpl.get();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer productId = getRequiredIntegerParam(PRODUCT_ID, req, resp);
        if(productId == null) return;

        Product removedProduct = productManager.deleteProductByPK(productId);
        log.info("Product '{}' deleted", removedProduct.getName());

        resp.setStatus(200);
    }
}
