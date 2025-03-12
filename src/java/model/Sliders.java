/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quanpyke
 */
public class Sliders {
    int id;
    String title;
    String description;
    String img;
    int  status;
  
    public Sliders() {
    }

    public Sliders(int id, String description, String img, int status) {
        this.id = id;
        this.description = description;
        this.img = img;
        this.status = status;
    }

    public Sliders(int id, String title, String description, String img, int status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.img = img;
        this.status = status;
    }

    public Sliders(String description, String img, int status) {
        this.description = description;
        this.img = img;
        this.status = status;
    }

    public Sliders(String title, String description, String img, int status) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
