/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webthethao365.dao;

import com.mycompany.webthethao365.model.TaiKhoanModel;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dovan
 */
public class TaiKhoanDAO extends KetNoiCSDL {

    public TaiKhoanModel layTaiKhoanTheoTenDangNhap(String tenDangNhap) {
        String sql = "select * from tai_khoan where tai_khoan.ten_dang_nhap = ?";
        try {
            PreparedStatement st = ketNoi.prepareStatement(sql);
            st.setString(1, tenDangNhap);
            ResultSet rs = st.executeQuery();
            TaiKhoanModel taiKhoan = new TaiKhoanModel();
            while (rs.next()) {
                taiKhoan.setId(rs.getInt("id"));
                taiKhoan.setLoaiTK(rs.getInt("loaitk"));
                taiKhoan.setMatKhau(rs.getString("mat_khau"));
                taiKhoan.setNgayDK(rs.getDate("ngaydk"));
                taiKhoan.setNguoiDungId(rs.getInt("nguoi_dung_id"));
                taiKhoan.setTenDangNhap(rs.getString("ten_dang_nhap"));
                return taiKhoan;
            }
        } catch (Exception e) {
            System.out.println("Lay tai khoan theo ten dang nhap gap loi!!!");
        }
        return null;
    }

    public boolean luuTaiKHoan(TaiKhoanModel taiKhoan) {
        if (taiKhoan.getId() == null) {
            String sql = "insert into tai_khoan(loaitk, mat_khau, ngaydk, ten_dang_nhap, nguoi_dung_id)\n"
                    + "values (?, ?, ?, ?, ?)";
            try {
                PreparedStatement st = ketNoi.prepareStatement(sql);
                st.setInt(1, taiKhoan.getLoaiTK());
                st.setString(2, taiKhoan.getMatKhau());
                st.setDate(3, new Date(taiKhoan.getNgayDK().getTime()));
                st.setString(4, taiKhoan.getTenDangNhap());
                st.setInt(5, taiKhoan.getNguoiDungId());
                if (st.executeUpdate() != 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String sql = "update tai_khoan \n"
                    + "set loaitk = ?, mat_khau = ?, ngaydk = ?, ten_dang_nhap = ?, nguoi_dung_id = ?\n"
                    + "where id = ?";
            try {
                PreparedStatement st = ketNoi.prepareStatement(sql);
                st.setInt(1, taiKhoan.getLoaiTK());
                st.setString(2, taiKhoan.getMatKhau());
                st.setDate(3, new Date(taiKhoan.getNgayDK().getTime()));
                st.setString(4, taiKhoan.getTenDangNhap());
                st.setInt(5, taiKhoan.getNguoiDungId());
                st.setInt(6, taiKhoan.getId());
                if (st.executeUpdate() != 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
