/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webthethao365.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author dovan
 */

public class TinTucModel implements Serializable{
    private Integer id;
    private String tieuDe;
    private String tomTat;
    private String noiDung;
    private Date ngayTao = new Date();
    private String hinh;
    private Integer taiKhoanId;
    private Integer theLoaiId;
    
    public TinTucModel() {
    }

    public TinTucModel(Integer id, String tieuDe, String tomTat, String noiDung, String hinh, Integer taiKhoanId, Integer theLoaiId) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.tomTat = tomTat;
        this.noiDung = noiDung;
        this.hinh = hinh;
        this.taiKhoanId = taiKhoanId;
        this.theLoaiId = theLoaiId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public Integer getTaiKhoanId() {
        return taiKhoanId;
    }

    public void setTaiKhoanId(Integer taiKhoanId) {
        this.taiKhoanId = taiKhoanId;
    }

    public Integer getTheLoaiId() {
        return theLoaiId;
    }

    public void setTheLoaiId(Integer theLoaiId) {
        this.theLoaiId = theLoaiId;
    }

    @Override
    public String toString() {
        return "TinTucModel{" + "id=" + id + ", tieuDe=" + tieuDe + ", tomTat=" + tomTat + ", noiDung=" + noiDung + ", ngayTao=" + ngayTao + ", hinh=" + hinh + ", taiKhoanId=" + taiKhoanId + ", theLoaiId=" + theLoaiId + '}';
    }
}
