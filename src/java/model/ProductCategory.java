/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ProductCategory {
      int product_category_id;
    String product_category_name;
    int is_active;

    public ProductCategory() {
    }

    public ProductCategory(int product_category_id, String product_category_name, int is_active) {
        this.product_category_id = product_category_id;
        this.product_category_name = product_category_name;
        this.is_active = is_active;
    }

    public int getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(int product_category_id) {
        this.product_category_id = product_category_id;
    }

    public String getProduct_category_name() {
        return product_category_name;
    }

    public void setProduct_category_name(String product_category_name) {
        this.product_category_name = product_category_name;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "ProductCategory{" + "product_category_id=" + product_category_id + ", product_category_name=" + product_category_name + ", is_active=" + is_active + '}';
    }
}
