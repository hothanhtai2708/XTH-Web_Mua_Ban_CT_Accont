package com.example.rentalshop_xtt.Api;

import com.example.rentalshop_xtt.Service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class productApi {
    @Autowired
    private productService service;
    @GetMapping("/views-product")
    public ResponseEntity<?> viewsProduct(){
        Map<String,Object> map = new HashMap<>();
        try {
            map.put("status",true);
            map.put("message","Call API product success !");
            map.put("data",service.findByAll());
            return ResponseEntity.ok(map);
        }catch (Exception e){
            map.put("status",false);
            map.put("message","Call API product error !");
            map.put("data",null);
            return ResponseEntity.ok(map);
        }
    }
}
