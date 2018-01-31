package pl.epoint.servlets.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.epoint.servlets.model.Product;

import static com.google.common.collect.Iterables.find;

@Singleton
public class ProductMemoryManagerImpl implements ProductManager {

    private static int productsCounter;

    private List<Product> products = new ArrayList<>();

    @PostConstruct
    void init() {
        insertProduct(new Product("Jaja", BigDecimal.valueOf(12.2)));
        insertProduct(new Product("Więcej jaj", BigDecimal.TEN));
        insertProduct(new Product("Kazjerka", BigDecimal.valueOf(8.25)));
        insertProduct(new Product("Śmietana", new BigDecimal(-1)));
    }


    @Override
    public List<Product> getProductsList() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public Product getProductByPK(Integer id) {
        return find(products, product ->
                product.getId().equals(id));
    }

    @Override
    public void insertProduct(Product product) {
        products.add(product);
        product.setId(productsCounter++);
    }

    @Override
    public void updateProduct(Product product) {
        Product productToUpdate = getProductByPK(product.getId());
        if(productToUpdate != null) {
            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
        }
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
    }

    @Override
    public Product deleteProductByPK(Integer id) {
        Product productToRemove = getProductByPK(id);

        if(productToRemove != null)
            deleteProduct(productToRemove);

        return productToRemove;
    }
}
