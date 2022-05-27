package com.enigma.enigmatboot.controller;

import com.enigma.enigmatboot.constant.ApiUrlConstant;
import com.enigma.enigmatboot.constant.ResponseMessage;
import com.enigma.enigmatboot.entity.Product;
import com.enigma.enigmatboot.service.ProductService;
import com.enigma.enigmatboot.utils.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Response<Product>> createProduct(@RequestBody Product customer){
        Response<Product> response = new Response<Product>();
        String message = String.format(ResponseMessage.DATA_INSERTED, "product");
        response.setMessage(message);

        response.setData(productService.saveProduct(customer));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable String customerId){
        return productService.getProductById(customerId);
    }

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header") })
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PutMapping
    public void updateProduct(@RequestBody Product customer){
        productService.saveProduct(customer);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam String id) {
        productService.deleteProduct(id);
    }


}
