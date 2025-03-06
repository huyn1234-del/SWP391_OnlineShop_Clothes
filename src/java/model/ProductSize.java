/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */
public class ProductSize {

    int size_id, product_id, quantity;
    String size_name;
    int weight;

    public ProductSize() {
    }

    public ProductSize(int size_id, int product_id, int quantity, int weight) {
        this.size_id = size_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.weight = weight;
    }

    public ProductSize(int size_id, int product_id, String size_name) {
        this.size_id = size_id;
        this.product_id = product_id;
        this.size_name = size_name;
    }

    public ProductSize(int size_id, int product_id, int quantity, String size_name) {
        this.size_id = size_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.size_name = size_name;
    }

    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
