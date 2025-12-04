package com.billgenrator.BillGenrator.Service;

import com.billgenrator.BillGenrator.DTO.BillResDTO;
import com.billgenrator.BillGenrator.DTO.OrderReqDTO;
import com.billgenrator.BillGenrator.DTO.ResponseDTO;
import com.billgenrator.BillGenrator.Model.Products;
import com.billgenrator.BillGenrator.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    TwillioService twillioService;

    public void saveProduct(Products product) {

        productRepo.save(product);

    }

    public String updateStock(int pid, int stock) {
        Products product = productRepo.findById(pid).orElseThrow(() -> new RuntimeException("Invalid Product ID: " + pid));
        productRepo.updateStock(pid,stock);
        return "product updated";
    }

    public BillResDTO createOrder(OrderReqDTO req) {
        Products product = productRepo.findById(req.getProductId()).orElseThrow( () -> new RuntimeException("Product Not found"+ req.getProductId()));

        //payment
        if(!isPyment()){
            BillResDTO billResDTO = new BillResDTO();

            twillioService.updateMsgText("+91" + req.getMobileNo(), "Payment failed..");

            return billResDTO.setMsg("Payment failed...");
        }

        if(req.getProductQuantity() > product.getThreshold()){
            twillioService.updateMsg("+91" + req.getMobileNo(), "Need to update your stock " );
            twillioService.updateMsgText("+91" + req.getMobileNo(), "Need to update your stock " );
        }

        if (req.getProductQuantity() > product.getThreshold()){

            BillResDTO billResDTO = new BillResDTO();
            return billResDTO.setMsg("Need to Update Stock ");

            }

        //check qty
        if(req.getProductQuantity() > product.getQuantity()){
            BillResDTO responseDTO = new BillResDTO();
            return responseDTO.setMsg("Order cant placed because stock not available");
        }

        BillResDTO responseDTO = getDto(req, product);
        product.setQuantity(product.getQuantity() - req.getProductQuantity());
        productRepo.save(product);

        twillioService.updateMsgText("+91" + req.getMobileNo(), "Payment success..");

        return  responseDTO;

    }

    private boolean isPyment() {
        double random = Math.random();
        return random < 0.8;
    }

    private BillResDTO getDto(OrderReqDTO req, Products product){
        int total =req.getProductQuantity() * product.getPrice() * 18 /  100;
        ResponseDTO response = new ResponseDTO();
        response.setBillNumber(1001);
        response.setName(req.getName());
        response.setMobileNo(req.getMobileNo());
        response.setProductid(req.getProductId());
        response.setProductName(product.getName());
        response.setQuantity(req.getProductQuantity());
        response.setPrice(product.getPrice());
        response.setGst(18.00);
        response.setSubtotal(req.getProductQuantity() * product.getPrice());
        response.setTotal(total + response.getSubtotal());

        BillResDTO billResDTO = new BillResDTO();
        billResDTO.setResponse(response);
        billResDTO.setMsg("order created successfully");
        return billResDTO;
    }

    public String getReport() {
        List<Products> products = productRepo.findAll();
        if(products.isEmpty()) return "id,productName,Quantity,price,gst,threshold\n";

        StringBuilder sb = new StringBuilder("id,productName,Quantity,price,gst,threshold\n");

        for(Products p : products){
            sb.append(p.getPid()).append(",");
            sb.append(p.getName()).append(",");
            sb.append(p.getQuantity()).append(",");
            sb.append(p.getPrice()).append(",");
            sb.append(p.getGst()).append(",");
            sb.append(p.getThreshold()).append("\n");
        }

        return sb.toString();
    }
}
