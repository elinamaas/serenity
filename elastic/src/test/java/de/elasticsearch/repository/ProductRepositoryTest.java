package de.elasticsearch.repository;

import de.elasticsearch.config.Config;
import de.elasticsearch.domain.Product;
import de.elasticsearch.service.ProductService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by elinazarisheva on 15/09/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class}, loader = AnnotationConfigContextLoader.class)
public class ProductRepositoryTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ProductService productService;

//    @Before
//    public void before() {
//        elasticsearchTemplate.deleteIndex(Product.class);
//        elasticsearchTemplate.createIndex(Product.class);
//
//        Product article = new Product("Spring Data Elasticsearch");
//        article.setAuthors(asList(johnSmith, johnDoe));
//        articleService.save(article);
//
//        article = new Product("Search engines");
//        article.setAuthors(asList(johnDoe));
//        productService.save(article);
//
//        article = new Article("Second Article About Elasticsearch");
//        article.setAuthors(asList(johnSmith));
//        articleService.save(article);
//    }

    @Test
    public void indexSinglProductEntity() {

        final Product product = new Product();
        product.setId("123455");
        product.setName("Wasser");
        productService.save(product);
        //lets try to search same record in elasticsearch
        Product indexProduct = productService.findOne(product.getId());
        assertThat(indexProduct, is(notNullValue()));
        assertThat(indexProduct.getId(), is(product.getId()));
    }

    @Test
    public void bulkIndexMultipleProductEntities() {
        final Product product1 = new Product();
        product1.setId("123455");
        product1.setName("Wasser");

        final Product product2 = new Product();
        product2.setId("123456");
        product2.setName("Apfel");

        productService.save(asList(product1,product2));

        final Product indexedProduct1 = productService.findOne(product1.getId());
        assertThat(product1.getId(), is(indexedProduct1.getId()));

        final Product indexedProduct2 = productService.findOne(product2.getId());
        assertThat(product2.getId(), is(indexedProduct2.getId()));

    }

    @Test
    public void findByProductName() {
        final Product product1 = new Product();
        product1.setId("123455");
        product1.setName("Wasser");

        final Product product2 = new Product();
        product2.setId("123456");
        product2.setName("Apfel");

        productService.save(asList(product1,product2));

        List<Product> productList1 = productService.findByName("Wasser");
        List<Product> productList2 = productService.findByName("wasser");
        List<Product> productList3 = productService.findByName("Waasse");

        assertThat(productList1.size(), is(notNullValue()));
        assertThat(productList2.size(), is(notNullValue()));
        assertThat(productList3.size(), is(0));


    }

    @Test
    public void findByProductNameLike() {
        final Product product1 = new Product();
        product1.setId("123455");
        product1.setName("Wasser");

        final Product product2 = new Product();
        product2.setId("123456");
        product2.setName("Apfel");

        productService.save(asList(product1,product2));

        List<Product> productList3 = productService.findByNameLike("Wasse");

        assertThat(productList3.size(), is(not(0)));


    }
}
