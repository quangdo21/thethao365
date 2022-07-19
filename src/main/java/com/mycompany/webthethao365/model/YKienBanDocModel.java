/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webthethao365.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dovan
 */
public class YKienBanDocModel implements Serializable{
    private Integer id;
    private String hoTen;
    private String email;
    private String noiDung;
    private Date ngay = new Date();

    public YKienBanDocModel() {
    }

    public YKienBanDocModel(Integer id, String hoTen, String email, String noiDung) {
        this.id = id;
        this.hoTen = hoTen;
        this.email = email;
        this.noiDung = noiDung;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    @Override
    public String toString() {
        return "YKienBanDocModel{" + "id=" + id + ", hoTen=" + hoTen + ", email=" + email + ", noiDung=" + noiDung + ", ngay=" + ngay + '}';
    }
}
