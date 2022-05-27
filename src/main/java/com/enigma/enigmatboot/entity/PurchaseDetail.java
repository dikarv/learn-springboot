package com.enigma.enigmatboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_purchase_detail")
public class PurchaseDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "purchase_detail_id")
    private String id;
    private Double priceSell;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    @JsonIgnoreProperties("purchaseDetails")
    private Purchase purchase;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public PurchaseDetail() {
    }

    public PurchaseDetail(String id, Double priceSell, Integer quantity, Purchase purchase, Product product) {
        this.id = id;
        this.priceSell = priceSell;
        this.quantity = quantity;
        this.purchase = purchase;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(Double priceSell) {
        this.priceSell = priceSell;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
