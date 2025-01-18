/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class CustomerAddress {
    private int customer_addresses_id;
    private String address;
    private int province_id;
    private String province_name;
    private int district_id;
    private String district_name;
    private String ward_code;
    private String ward_name;
    private String phone;
    private String receiver_name;
    private boolean is_default;
    private int customer_id;

    public CustomerAddress() {
    }

    public CustomerAddress(String address, int province_id, String province_name, int district_id, String district_name, String ward_code, String ward_name, String phone, String receiver_name, boolean is_default, int customer_id) {
        this.address = address;
        this.province_id = province_id;
        this.province_name = province_name;
        this.district_id = district_id;
        this.district_name = district_name;
        this.ward_code = ward_code;
        this.ward_name = ward_name;
        this.phone = phone;
        this.receiver_name = receiver_name;
        this.is_default = is_default;
        this.customer_id = customer_id;
    }

    public CustomerAddress(int customer_addresses_id, String address, int province_id, String province_name, int district_id, String district_name, String ward_code, String ward_name, String phone, String receiver_name, boolean is_default, int customer_id) {
        this.customer_addresses_id = customer_addresses_id;
        this.address = address;
        this.province_id = province_id;
        this.province_name = province_name;
        this.district_id = district_id;
        this.district_name = district_name;
        this.ward_code = ward_code;
        this.ward_name = ward_name;
        this.phone = phone;
        this.receiver_name = receiver_name;
        this.is_default = is_default;
        this.customer_id = customer_id;
    }

    public int getCustomer_addresses_id() {
        return customer_addresses_id;
    }

    public void setCustomer_addresses_id(int customer_addresses_id) {
        this.customer_addresses_id = customer_addresses_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getWard_code() {
        return ward_code;
    }

    public void setWard_code(String ward_code) {
        this.ward_code = ward_code;
    }

    public String getWard_name() {
        return ward_name;
    }

    public void setWard_name(String ward_name) {
        this.ward_name = ward_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
