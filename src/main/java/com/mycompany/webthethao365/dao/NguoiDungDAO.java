/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webthethao365.dao;

import com.mycompany.webthethao365.model.NguoiDungModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dovan
 */
public class NguoiDungDAO extends KetNoiCSDL {

    public NguoiDungModel layNguoiDungTheoId(Integer id) {
        String sql = "select * from nguoi_dung where nguoi_dung.id = ?";
        try {
            PreparedStatement st = ketNoi.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            NguoiDungModel nguoiDung = new NguoiDungModel();
            while (rs.next()) {
                nguoiDung.setId(rs.getInt("id"));
                nguoiDung.setEmail(rs.getString("email"));
                nguoiDung.setGioiTinh(rs.getBoolean("gioi_tinh"));
                nguoiDung.setHoTen(rs.getString("ho_ten"));
                nguoiDung.setNgaySinh(rs.getDate("ngay_sinh"));
                nguoiDung.setSoDT(rs.getString("sodt"));
                return nguoiDung;
            }
        } catch (Exception e) {
            System.out.println("Lay nguoi dung theo ID gap loi!!!");
        }
        return null;
    }

    public NguoiDungModel luuNguoiDung(NguoiDungModel nguoiDung) {
        if (nguoiDung.getId() == null) {
            String sql = "insert into nguoi_dung(email, gioi_tinh, ho_ten, ngay_sinh, sodt)\n"
                    + "output inserted.id \n"
                    + "values (?, ?, ?, ?, ?)";
            try {
                PreparedStatement st = ketNoi.prepareStatement(sql);
                st.setString(1, nguoiDung.getEmail());
                st.setBoolean(2, nguoiDung.getGioiTinh());
                st.setString(3, nguoiDung.getHoTen());
                st.setDate(4, new java.sql.Date(nguoiDung.getNgaySinh().getTime()));
                st.setString(5, nguoiDung.getSoDT());
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    nguoiDung = layNguoiDungTheoId(rs.getInt("id"));
                    return nguoiDung;
                }
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String sql = "update nguoi_dung\n"
                    + "set email = ?, gioi_tinh = ?, ho_ten = ?, ngay_sinh = ?, sodt = ?\n"
                    + "where nguoi_dung.id = ?";
            try {
                PreparedStatement st = ketNoi.prepareStatement(sql);
                st.setString(1, nguoiDung.getEmail());
                st.setBoolean(2, nguoiDung.getGioiTinh());
                st.setString(3, nguoiDung.getHoTen());
                st.setDate(4, new java.sql.Date(nguoiDung.getNgaySinh().getTime()));
                st.setString(5, nguoiDung.getSoDT());
                st.setInt(6, nguoiDung.getId());
                st.executeUpdate();
                return layNguoiDungTheoId(nguoiDung.getId());
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
