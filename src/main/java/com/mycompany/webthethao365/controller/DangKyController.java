/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webthethao365.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycompany.webthethao365.dao.NguoiDungDAO;
import com.mycompany.webthethao365.dao.TaiKhoanDAO;
import com.mycompany.webthethao365.model.NguoiDungModel;
import com.mycompany.webthethao365.model.TaiKhoanModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dovan
 */
//          /api/dangky
public class DangKyController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DangKyController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DangKyController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String ma = "E";
        String thongBao = "Đăng ký không thành công!";
        try {
            thongTinNguoiDung nguoidung = gson.fromJson(request.getReader(), thongTinNguoiDung.class);
            NguoiDungDAO nguoidungdao = new NguoiDungDAO();
            NguoiDungModel nguoidungmodel = nguoidungdao.luuNguoiDung(nguoidung.getNguoidung());
            TaiKhoanModel taikhoanmodel = nguoidung.getTaikhoan();
            TaiKhoanDAO taikhoandao = new TaiKhoanDAO();
            if (taikhoandao.layTaiKhoanTheoTenDangNhap(taikhoanmodel.getTenDangNhap()) == null) {
                taikhoanmodel.setNguoiDungId(nguoidungmodel.getId());
                taikhoanmodel.setMatKhau(MaHoa.chuyenDoiChuoi(taikhoanmodel.getMatKhau()));
                taikhoandao.luuTaiKHoan(taikhoanmodel);
                thongBao = "Đăng ký thành công!";
                ma = "OK";
            } else {
                thongBao = "Tài khoản đã tồn tại!";
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DangKyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter out = response.getWriter();
        JsonObject chuoiJson = new JsonObject();
        chuoiJson.addProperty("ma", ma);
        chuoiJson.addProperty("noiDung", thongBao);
        out.print(gson.toJson(chuoiJson));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private class thongTinNguoiDung {

        private TaiKhoanModel taikhoan;
        private NguoiDungModel nguoidung;

        public thongTinNguoiDung() {
        }

        public thongTinNguoiDung(TaiKhoanModel taikhoan, NguoiDungModel nguoidung) {
            this.taikhoan = taikhoan;
            this.nguoidung = nguoidung;
        }

        public TaiKhoanModel getTaikhoan() {
            return taikhoan;
        }

        public void setTaikhoan(TaiKhoanModel taikhoan) {
            this.taikhoan = taikhoan;
        }

        public NguoiDungModel getNguoidung() {
            return nguoidung;
        }

        public void setNguoidung(NguoiDungModel nguoidung) {
            this.nguoidung = nguoidung;
        }

    }
}
