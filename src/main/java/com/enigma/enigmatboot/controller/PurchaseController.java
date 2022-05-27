package com.enigma.enigmatboot.controller;

import com.enigma.enigmatboot.constant.ResponseMessage;
import com.enigma.enigmatboot.entity.Purchase;
import com.enigma.enigmatboot.service.PurchaseService;
import com.enigma.enigmatboot.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Response<Purchase>> customerPurchase(@RequestBody Purchase purchase){
        String message = String.format(ResponseMessage.DATA_INSERTED, "purchase");
        Response<Purchase> response = new Response<>();
        response.setMessage(message);
        response.setData(purchaseService.transaction(purchase));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{id}")
    public Purchase getPurchase(@PathVariable  String id){
         return purchaseService.getTransactionByiD(id);
    }

}
