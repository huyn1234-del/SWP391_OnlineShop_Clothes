/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Slider {

    int slider_id;
    String tittle;
    String description;
    String image_url;
    String is_active;

    public Slider() {
    }

    public Slider(int slider_id, String tittle, String description, String image_url, String is_active) {
        this.slider_id = slider_id;
        this.tittle = tittle;
        this.description = description;
        this.image_url = image_url;
        this.is_active = is_active;
    }

    public Slider(int slider_id, String tittle, String description, String is_active) {
        this.slider_id = slider_id;
        this.tittle = tittle;
        this.description = description;
        this.is_active = is_active;
    }
    
    

    public int getId() {
        return slider_id;
    }

    public void setId(int id) {
        this.slider_id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
    
    @Override
    public String toString() {
        return "Slider{" + "id=" + slider_id + ", tittle=" + tittle + ", description=" + description + ", image_url=" + image_url + ", is_active=" + is_active + '}';
    }

}
