/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webthethao365.model;

import java.io.Serializable;

/**
 *
 * @author dovan
 */
public class TheLoaiModel implements Serializable{
    private Integer id;
    private String ten;

    public TheLoaiModel() {
    }

    public TheLoaiModel(Integer id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return "TheLoaiModel{" + "id=" + id + ", ten=" + ten + '}';
    }
    
}
