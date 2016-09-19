package de.elasticsearch.service;

import de.elasticsearch.domain.Product;
import de.elasticsearch.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by elinazarisheva on 15/09/16.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findOne(String id) {
        return productRepository.findOne(id);
    }

    @Override
    public void delete(Product article) {
        productRepository.delete(article);
    }

    @Override
    public Iterable<Product> save(List<Product> products){
        return productRepository.save(products);
    }

    @Override
    public List<Product> findByName(String name){
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByNameLike(String name) {
        return productRepository.findByNameLike(name);
    }

//    @Override
//    public Page<Product> findByProductName(String name, Pageable pageable) {
//        return productRepository.findByProductName(name, pageable);
//    }

}
