package pl.epoint.servlets.dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.util.List;

import pl.epoint.servlets.model.Product;

@Log4j2
class ProductsDao { //klasa i jej pakiety moga być

    private static final String TABLE = "products";

    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_PRICE = "price";

    private final JdbcTemplate template;

    ProductsDao(JdbcTemplate template) {
        this.template = template;
        initTable();
    }

    List<Product> getProductsList() {
        return template.query("select * from " + TABLE, productRowMapper);
    }

    Product getProductByPK(Integer id) {
        String query = String.format("select * from %s where %s = ?", TABLE, COL_ID);
        try {
            return template.queryForObject(query, productRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    void insertProduct(Product product) {
        String query = String.format("insert into %s(%s,%s) values (?,?)", TABLE, COL_NAME, COL_PRICE);
        template.update(query, product.getName(), product.getPrice());
        /* To raczej nie jest potrzebne:
        Integer id = template.queryForObject("select last_insert_id()", Integer.class);
        product.setId(id); */
    }

    void updateProduct(Product product) {
        String query = String.format(
                "update %s set %s =?, %s = ? where %s = ?",
                TABLE, COL_NAME, COL_PRICE, COL_ID);
        template.update(query, product.getName(), product.getPrice(), product.getId());
    }

    void deleteProduct(Product product) {
        String query = String.format("delete from %s where %s = ?", TABLE, COL_ID);
        template.update(query, product.getId());
    }

    Product deleteProductByPK(Integer id) {
        Product product = getProductByPK(id);
        deleteProduct(product); //reużycie kodu
        return product;
    }

    private RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        int id = rs.getInt(COL_ID);
        String name = rs.getString(COL_NAME);
        BigDecimal price = rs.getBigDecimal(COL_PRICE);
        return new Product(id, name, price);
    };

    private void initTable() {
        log.info("create table if not exists " + TABLE + "("
                + COL_ID + " int not null AUTO_INCREMENT PRIMARY KEY, "
                + COL_NAME + " varchar(12) not null, "
                + COL_PRICE + " decimal(6,2) not null,"
                + "CHECK(" + COL_PRICE + " >= '0.00'))");
    }

}
