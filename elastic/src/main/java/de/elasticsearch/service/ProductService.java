package de.elasticsearch.service;

import de.elasticsearch.domain.Product;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by elinazarisheva on 15/09/16.
 */
public interface ProductService {

    Product save(Product product);
    Iterable<Product> save (List<Product> products);

    Product findOne(String id);

    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);

    void delete(Product product);
}
