package com.example.rentalshop_xtt.Repository;

import com.example.rentalshop_xtt.Data.Entity.ProductE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepo extends JpaRepository<ProductE,Integer> {

}
