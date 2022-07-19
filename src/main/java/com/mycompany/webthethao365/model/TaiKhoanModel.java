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
public class TaiKhoanModel implements Serializable{
    private Integer id;
    private String tenDangNhap;
    private String matKhau;
    private Date ngayDK = new Date();
    private Integer loaiTK = 1;
    private Integer nguoiDungId;

    public TaiKhoanModel() {
    }

    public TaiKhoanModel(Integer id, String tenDangNhap, String matKhau, Integer nguoiDungId) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.nguoiDungId = nguoiDungId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }

    public Integer getLoaiTK() {
        return loaiTK;
    }

    public void setLoaiTK(Integer loaiTK) {
        this.loaiTK = loaiTK;
    }

    public Integer getNguoiDungId() {
        return nguoiDungId;
    }

    public void setNguoiDungId(Integer nguoiDungId) {
        this.nguoiDungId = nguoiDungId;
    }

    @Override
    public String toString() {
        return "TaiKhoanModel{" + "id=" + id + ", tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", ngayDK=" + ngayDK + ", loaiTK=" + loaiTK + ", nguoiDungId=" + nguoiDungId + '}';
    }
}
