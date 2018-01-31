package pl.epoint.servlets.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private BigDecimal price;
}
