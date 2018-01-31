package pl.epoint.servlets.dao;

import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.List;

import pl.epoint.servlets.model.Product;

@Log4j2
public class ProductDatabaseManagerImpl implements ProductManager {

    private static ProductDatabaseManagerImpl singleton;

    private final ProductsDao dao = new ProductsDao(
            DatabaseConnectionManager.get().getJdbcTemplate());

    public static ProductDatabaseManagerImpl get() {
        if(singleton == null) {
            singleton = new ProductDatabaseManagerImpl();
        }

        return singleton;
    }

    private ProductDatabaseManagerImpl() {
        insertProduct(new Product("Jaja", new BigDecimal(12.2)));
        insertProduct(new Product("Więcej jaj", BigDecimal.TEN));
        insertProduct(new Product("Kazjerka", new BigDecimal(8.25)));
        insertProduct(new Product("Śmietana", new BigDecimal(-1)));
    }

    @Override
    public List<Product> getProductsList() {
        return dao.getProductsList();
    }

    @Override
    public Product getProductByPK(Integer id) {
        return dao.getProductByPK(id);
    }

    @Override
    public void insertProduct(Product product) {
        dao.insertProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        dao.updateProduct(product);
    }

    @Override
    public void deleteProduct(Product product) {
        dao.deleteProduct(product);
    }

    @Override
    public Product deleteProductByPK(Integer id) {
        return dao.deleteProductByPK(id);
    }

}
