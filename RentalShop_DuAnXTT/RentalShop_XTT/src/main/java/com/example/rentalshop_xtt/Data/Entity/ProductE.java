package com.example.rentalshop_xtt.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Table(name = "Product")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int product_id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "price")
    private float price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "pictures")
    private String pictures;

    @Column(name = "slug_url")
    private String slug_url;
}
