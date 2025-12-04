package com.billgenrator.BillGenrator.DTO;

public class ProductDTO {


    private String name;
    private int price;
    private int gst = 18;
    private int quantity;
    private int threshold;

    public ProductDTO(){}

    public ProductDTO( String name, int price, int gst, int quantity, int threshold) {

        this.name = name;
        this.price = price;
        this.gst = gst;
        this.quantity = quantity;
        this.threshold = threshold;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGst() {
        return gst;
    }

    public void setGst(int gst) {
        this.gst = gst;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
