/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webthethao365.dao;

import com.mycompany.webthethao365.model.TinTucModel;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dovan
 */
public class TinTucDAO extends KetNoiCSDL {

    public TinTucModel layTinTucTheoId(Integer id) {
        String sql = "select * from tin_tuc where id = ?";
        try {
            TinTucModel tin = new TinTucModel();
            PreparedStatement st = ketNoi.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                tin.setId(rs.getInt("id"));
                tin.setHinh(rs.getString("hinh"));
                tin.setNgayTao(rs.getDate("ngay_tao"));
                tin.setNoiDung(rs.getString("noi_dung"));
                tin.setTieuDe(rs.getString("tieu_de"));
                tin.setTomTat(rs.getString("tom_tat"));
                tin.setTaiKhoanId(rs.getInt("tai_khoan_id"));
                tin.setTheLoaiId(rs.getInt("the_loai_id"));
                return tin;
            }
        } catch (Exception e) {
            System.out.println("Lay tin tuc theo ID gap loi!!!");
        }
        return null;
    }

    public List<TinTucModel> lay10TinMoi() {
        List<TinTucModel> danhSachTin = new ArrayList<>();
        String sql = "select top (10) * from tin_tuc order by tin_tuc.id desc";
        try {
            PreparedStatement st = ketNoi.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TinTucModel tin = new TinTucModel();
                tin.setId(rs.getInt("id"));
                tin.setHinh(rs.getString("hinh"));
                tin.setNgayTao(rs.getDate("ngay_tao"));
                tin.setNoiDung(rs.getString("noi_dung"));
                tin.setTieuDe(rs.getString("tieu_de"));
                tin.setTomTat(rs.getString("tom_tat"));
                tin.setTaiKhoanId(rs.getInt("tai_khoan_id"));
                tin.setTheLoaiId(rs.getInt("the_loai_id"));
                danhSachTin.add(tin);
            }
            return danhSachTin;
        } catch (Exception e) {
            System.out.println("Lay danh sach 10 tin moi gap loi!!!");
        }
        return null;
    }

//    Lấy 1 tin tức mới nhất theo thể loại
    public TinTucModel layTinTucHot1(Integer id) {
        String sql = "select top (1) * from tin_tuc where tin_tuc.the_loai_id = ? order by tin_tuc.id desc";
        try {
            TinTucModel tin = new TinTucModel();
            PreparedStatement st = ketNoi.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                tin.setId(rs.getInt("id"));
                tin.setHinh(rs.getString("hinh"));
                tin.setNgayTao(rs.getDate("ngay_tao"));
                tin.setNoiDung(rs.getString("noi_dung"));
                tin.setTieuDe(rs.getString("tieu_de"));
                tin.setTomTat(rs.getString("tom_tat"));
                tin.setTaiKhoanId(rs.getInt("tai_khoan_id"));
                tin.setTheLoaiId(rs.getInt("the_loai_id"));
                return tin;
            }
        } catch (Exception e) {
            System.out.println("Lay tin tuc theo do hot gap loi!!!");
        }
        return null;
    }

    //    Lấy 3 tin tức mới tiếp theo theo thể loại
    public List<TinTucModel> layTinTucHot3(Integer id) {
        String sql = "select * from tin_tuc where tin_tuc.the_loai_id	 = ? order by tin_tuc.id desc offset 1 rows fetch next 3 rows only";
        try {
            List<TinTucModel> arraylist = new ArrayList();
            PreparedStatement st = ketNoi.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TinTucModel tin = new TinTucModel();
                tin.setId(rs.getInt("id"));
                tin.setHinh(rs.getString("hinh"));
                tin.setNgayTao(rs.getDate("ngay_tao"));
                tin.setNoiDung(rs.getString("noi_dung"));
                tin.setTieuDe(rs.getString("tieu_de"));
                tin.setTomTat(rs.getString("tom_tat"));
                tin.setTaiKhoanId(rs.getInt("tai_khoan_id"));
                tin.setTheLoaiId(rs.getInt("the_loai_id"));
                arraylist.add(tin);
            }
            return arraylist;
        } catch (Exception e) {
            System.out.println("Lay tin tuc theo do hot gap loi!!!");
        }
        return null;
    }

    public List<TinTucModel> layTinTucTheoTheLoai(Integer id) {
        String sql = "select * from tin_tuc where tin_tuc.the_loai_id = ? order by tin_tuc.id desc";
        try {
            List<TinTucModel> arraylist = new ArrayList();
            PreparedStatement st = ketNoi.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TinTucModel tin = new TinTucModel();
                tin.setId(rs.getInt("id"));
                tin.setHinh(rs.getString("hinh"));
                tin.setNgayTao(rs.getDate("ngay_tao"));
                tin.setNoiDung(rs.getString("noi_dung"));
                tin.setTieuDe(rs.getString("tieu_de"));
                tin.setTomTat(rs.getString("tom_tat"));
                tin.setTaiKhoanId(rs.getInt("tai_khoan_id"));
                tin.setTheLoaiId(rs.getInt("the_loai_id"));
                arraylist.add(tin);
            }
            return arraylist;
        } catch (Exception e) {
            System.out.println("Lay tin tuc theo do hot gap loi!!!");
        }
        return null;
    }

    // Lấy hết tin tức
    public List<TinTucModel> layTatCaTinTuc() {
        List<TinTucModel> list = new ArrayList<>();
        String sql = "SELECT * FROM tin_tuc order by tin_tuc.id desc";
        try {
            PreparedStatement st = ketNoi.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TinTucModel c = new TinTucModel();
                c.setId(rs.getInt("id"));
                c.setTieuDe(rs.getString("tieu_de"));
                c.setTomTat(rs.getString("tom_tat"));
                c.setNoiDung(rs.getString("noi_dung"));
                c.setNgayTao(rs.getDate("ngay_tao"));
                c.setHinh(rs.getString("hinh"));
                c.setTaiKhoanId(rs.getInt("tai_khoan_id"));
                c.setTheLoaiId(rs.getInt("the_loai_id"));
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    // Tìm tin tức theo tiêu đề (kq chứa tieuDe)
    public List<TinTucModel> layTinTucTheoTieuDe(String tieuDe) {
        List<TinTucModel> list = new ArrayList<>();
        String sql = "select * from tin_tuc where tieu_de like ? order by tin_tuc.id desc";
        try {
            PreparedStatement st = ketNoi.prepareStatement(sql);
            st.setString(1, "%" + tieuDe + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TinTucModel c = new TinTucModel();
                c.setId(rs.getInt("id"));
                c.setTieuDe(rs.getString("tieu_de"));
                c.setTomTat(rs.getString("tom_tat"));
                c.setNoiDung(rs.getString("noi_dung"));
                c.setHinh(rs.getString("hinh"));
                c.setNgayTao(rs.getDate("ngay_tao"));
                c.setTaiKhoanId(rs.getInt("tai_khoan_id"));
                c.setTheLoaiId(rs.getInt("the_loai_id"));
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    // Xóa tin tức
    public void xoaTinTuc(int id) {
        String sql = "DELETE FROM tin_tuc WHERE id = ?";
        try {
            PreparedStatement st = ketNoi.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    Luu tin tuc
    public boolean luuTinTuc(TinTucModel tinTuc) {
        if (tinTuc.getId() == null) {
            String sql = "insert into tin_tuc(hinh, ngay_tao, noi_dung, tieu_de, tom_tat, tai_khoan_id, the_loai_id)\n"
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement st = ketNoi.prepareStatement(sql);
                st.setString(1, tinTuc.getHinh());
                st.setDate(2, new Date(tinTuc.getNgayTao().getTime()));
                st.setString(3, tinTuc.getNoiDung());
                st.setString(4, tinTuc.getTieuDe());
                st.setString(5, tinTuc.getTomTat());
                st.setInt(6, tinTuc.getTaiKhoanId());
                st.setInt(7, tinTuc.getTheLoaiId());
                if (st.executeUpdate() != 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(NguoiDungDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String sql = "update tin_tuc \n"
                    + "set hinh = ?, ngay_tao = ?, noi_dung = ?, tieu_de = ?, tom_tat = ?, tai_khoan_id = ?, the_loai_id = ?\n"
                    + "where id = ?";
            try {
                PreparedStatement st = ketNoi.prepareStatement(sql);
                st.setString(1, tinTuc.getHinh());
                st.setDate(2, new Date(tinTuc.getNgayTao().getTime()));
                st.setString(3, tinTuc.getNoiDung());
                st.setString(4, tinTuc.getTieuDe());
                st.setString(5, tinTuc.getTomTat());
                st.setInt(6, tinTuc.getTaiKhoanId());
                st.setInt(7, tinTuc.getTheLoaiId());
                st.setInt(8, tinTuc.getId());
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
