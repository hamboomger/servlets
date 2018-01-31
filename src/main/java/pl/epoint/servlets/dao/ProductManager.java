package pl.epoint.servlets.dao;

import java.util.List;
import java.util.Optional;

import pl.epoint.servlets.model.Product;

public interface ProductManager {
    List<Product> getProductsList();
    Product getProductByPK(Integer id);
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    Product deleteProductByPK(Integer id);
}
