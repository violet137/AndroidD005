package com.greenacademy.ga_finalprojecthm.model;

import java.util.ArrayList;

/**
 * Created by thepa on 12/2/2017.
 */

public class ProductDetailsInWishlist {
    private int productID;
    private String productName;
    private float productPrice;
    private float productPriceSale;
    private String linkImage;
    private String productColor;
    private String productSize;
    private int productQuantity;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public float getProductPriceSale() {
        return productPriceSale;
    }

    public void setProductPriceSale(float productPriceSale) {
        this.productPriceSale = productPriceSale;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
