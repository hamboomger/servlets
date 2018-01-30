package pl.epoint.servlets.model;

import com.google.common.base.Preconditions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;

    public Product(Integer id, String name, BigDecimal price) {
        Preconditions.checkNotNull(name, "Name argument is null");
        Preconditions.checkNotNull(price, "Price argument is null");

        this.id = id;
        this.name = name;
        this.price = price.setScale(2, RoundingMode.UP);
    }

    public Product(String name, BigDecimal price) {
        this(null, name, price);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
