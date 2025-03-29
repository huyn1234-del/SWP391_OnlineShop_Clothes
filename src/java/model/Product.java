package model;

public class Product {
    int product_id;
    String product_name;
    int price;
    int total_quantity;
    String description;
    String thumbnail;
    boolean is_active;
    int rated_star;
    int brand_id;
    int product_category_id;

    public Product() {
    }
    
    public Product(int product_id, String product_name, int price, int total_quantity, String description, String thumbnail, boolean is_active, int rated_star, int brand_id, int product_category_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.total_quantity = total_quantity;
        this.description = description;
        this.thumbnail = thumbnail;
        this.is_active = is_active;
        this.rated_star = rated_star;
        this.brand_id = brand_id;
        this.product_category_id = product_category_id;
    }
    
    public Product(String product_name, int price, int total_quantity, String description, String thumbnail, boolean is_active, int rated_star, int brand_id, int product_category_id) {
        this.product_name = product_name;
        this.price = price;
        this.total_quantity = total_quantity;
        this.description = description;
        this.thumbnail = thumbnail;
        this.is_active = is_active;
        this.rated_star = rated_star;
        this.brand_id = brand_id;
        this.product_category_id = product_category_id;
    }
    
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    // Removed discount methods

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public int getRated_star() {
        return rated_star;
    }

    public void setRated_star(int rated_star) {
        this.rated_star = rated_star;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(int product_category_id) {
        this.product_category_id = product_category_id;
    }
}
