package pl.epoint.servlets.dao;

import lombok.extern.log4j.Log4j2;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.math.BigDecimal;
import java.util.List;

import pl.epoint.servlets.model.Product;

@Log4j2
@Singleton
public class ProductDatabaseManagerImpl implements ProductManager {

    @EJB
    private DatabaseConnectionManager databaseConnectionManager;

    private ProductsDao dao;

    @PostConstruct
    void init() {
        dao = new ProductsDao(databaseConnectionManager.getJdbcTemplate());
        insertProduct(new Product("Jaja", BigDecimal.valueOf(12.2)));
        insertProduct(new Product("Więcej jaj", BigDecimal.TEN));
        insertProduct(new Product("Kazjerka", BigDecimal.valueOf((8.25))));
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
