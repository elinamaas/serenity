package de.elasticsearch.repository;

import de.elasticsearch.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by elinazarisheva on 15/09/16.
 */

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);

//    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
//    Page<Product> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
}
