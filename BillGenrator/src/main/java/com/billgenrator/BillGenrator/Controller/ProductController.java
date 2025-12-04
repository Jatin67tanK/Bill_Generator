package com.billgenrator.BillGenrator.Controller;

import com.billgenrator.BillGenrator.DTO.OrderReqDTO;
import com.billgenrator.BillGenrator.DTO.ProductDTO;
import com.billgenrator.BillGenrator.Model.Products;
import com.billgenrator.BillGenrator.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    public void saveProduct(@RequestBody Products product){
        productService.saveProduct(product);
    }

    //http://localhost:8080/product/update-product/6/2
    @PostMapping("/update-stock/{pid}/{stock}")
    public ResponseEntity<?> updateStock(@PathVariable int pid, @PathVariable int stock){
        try{
           return ResponseEntity.ok(productService.updateStock(pid, stock));
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/place-order")
    public ResponseEntity<?> createOrder(@RequestBody OrderReqDTO dto){
        try {
            return ResponseEntity.ok(productService.createOrder(dto));
        }
        catch (RuntimeException e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("/get-today-report")
    public ResponseEntity<?> getReport(){
        try{
            return ResponseEntity.ok(productService.getReport());
        }catch (RuntimeException e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
