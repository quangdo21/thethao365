/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webthethao365.dao;

import com.mycompany.webthethao365.model.YKienBanDocModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dovan
 */
public class YKienBanDocDAO extends KetNoiCSDL {
    
    public List<YKienBanDocModel> layTatCaYKien(){
        String sql = "select * from y_kien_ban_doc order by id desc";
        List<YKienBanDocModel> danhSachYKien = new ArrayList<>();
        try {
            PreparedStatement st = ketNoi.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                YKienBanDocModel ykien = new YKienBanDocModel();
                ykien.setId(rs.getInt("id"));
                ykien.setEmail(rs.getString("email"));
                ykien.setHoTen(rs.getString("ho_ten"));
                ykien.setNgay(rs.getDate("ngay"));
                ykien.setNoiDung(rs.getString("noi_dung"));
                danhSachYKien.add(ykien);
            }
            return danhSachYKien;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean luuYKien(YKienBanDocModel yKien) {
        String sql = "insert into y_kien_ban_doc(email, ho_ten, ngay, noi_dung)\n"
                + "values (?, ?, ?, ?)";
        try {
            PreparedStatement st = ketNoi.prepareStatement(sql);
            st.setString(1, yKien.getEmail());
            st.setString(2, yKien.getHoTen());
            st.setDate(3, new Date(yKien.getNgay().getTime()));
            st.setString(4, yKien.getNoiDung());
            if (st.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NguoiDungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
