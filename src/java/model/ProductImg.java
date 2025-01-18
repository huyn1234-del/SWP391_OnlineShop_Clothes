/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ProductImg {
    int product_image_id, product_id;
    String image_url;
    int is_active;

    public ProductImg() {
    }

    public ProductImg(int product_image_id, int product_id, String image_url, int is_active) {
        this.product_image_id = product_image_id;
        this.product_id = product_id;
        this.image_url = image_url;
        this.is_active = is_active;
    }

    public int getProduct_image_id() {
        return product_image_id;
    }

    public void setProduct_image_id(int product_image_id) {
        this.product_image_id = product_image_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }
}
