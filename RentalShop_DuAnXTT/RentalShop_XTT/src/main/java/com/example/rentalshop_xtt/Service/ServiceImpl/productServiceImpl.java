package com.example.rentalshop_xtt.Service.ServiceImpl;

import com.example.rentalshop_xtt.Data.Entity.ProductE;
import com.example.rentalshop_xtt.Repository.ProductRepo;
import com.example.rentalshop_xtt.Service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productServiceImpl implements productService {
@Autowired
private ProductRepo productRepo;
    @Override
    public List<ProductE> findByAll() {
        return productRepo.findAll();
    }
}
