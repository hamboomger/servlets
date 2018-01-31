package pl.epoint.servlets.servlets;

import lombok.extern.log4j.Log4j2;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.epoint.servlets.dao.ProductManager;
import pl.epoint.servlets.model.Product;

import static pl.epoint.servlets.util.ServletUtils.getRequiredIntegerParam;

//W projekcie raczej używamy Slf4j ale jest ok.
@Log4j2
public class ProductDeleteServlet extends HttpServlet {

    private static final String PRODUCT_ID = "productId";

    @EJB(beanName = "ProductDatabaseManagerImpl")
    private ProductManager productManager;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        Integer productId = getRequiredIntegerParam(PRODUCT_ID, req);

        Product removedProduct = productManager.deleteProductByPK(productId);
        log.info("Product '{}' deleted", removedProduct.getName());

        resp.setStatus(200);
    }
}
