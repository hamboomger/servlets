package pl.epoint.servlets.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.epoint.servlets.model.Product;

import static com.google.common.collect.Iterables.find;

public class ProductMemoryManagerImpl implements ProductManager {

    private static ProductMemoryManagerImpl daoSingleton;
    private static int productsCounter;

    private List<Product> products = new ArrayList<>();

    {
        insertProduct(new Product("Jaja", new BigDecimal(12.2)));
        insertProduct(new Product("Więcej jaj", BigDecimal.TEN));
        insertProduct(new Product("Kazjerka", new BigDecimal(8.25)));
        insertProduct(new Product("Śmietana", new BigDecimal(-1)));
    }

    public static ProductMemoryManagerImpl get() {
        if(daoSingleton == null)
            daoSingleton = new ProductMemoryManagerImpl();
        return daoSingleton;
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
