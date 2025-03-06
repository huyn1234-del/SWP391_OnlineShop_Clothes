/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */
public class SaleChart {
    private String label;
    private int value;
    private String brand;
    public SaleChart() {
    }

    public SaleChart(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public SaleChart(String label, int value, String brand) {
        this.label = label;
        this.value = value;
        this.brand = brand;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "SaleChart{" + "label=" + label + ", value=" + value + ", brand=" + brand + '}';
    }
    
    
}
