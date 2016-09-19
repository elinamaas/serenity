package de.elasticsearch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.MultiField;

/**
 * Created by elinazarisheva on 15/09/16.
 */
@Document(indexName = "product", type= "product")
public class Product {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = FieldType.String))
    private String name;

    public Product() {}

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return this.id; }

    public void setId(String id) {this.id = id; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name;}

}