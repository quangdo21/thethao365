/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webthethao365.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycompany.webthethao365.dao.TaiKhoanDAO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author dovan
 */
//          /api/taikhoan
public class TaiKhoanController extends HttpServlet {

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
            out.println("<title>Servlet TaiKhoanController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TaiKhoanController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        TaiKhoanModel taiKhoan = taiKhoanDAO.layTaiKhoanTheoTenDangNhap((String) session.getAttribute("TENDANGNHAP"));
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        JsonObject chuoiJson = new JsonObject();
        if (taiKhoan == null) {
            chuoiJson.addProperty("ma", "E");
        } else {
            chuoiJson.addProperty("ma", "OK");
            chuoiJson.addProperty("noiDung", taiKhoan.getLoaiTK());
            chuoiJson.addProperty("dulieu", taiKhoan.getTenDangNhap());
        }
        out.print(gson.toJson(chuoiJson));
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
        processRequest(request, response);
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
    
// Đổi mật khẩu
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
//        session.setAttribute("TENDANGNHAP", "admin");
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        TaiKhoanModel taiKhoan = taiKhoanDAO.layTaiKhoanTheoTenDangNhap((String) session.getAttribute("TENDANGNHAP"));
        Gson gson = new Gson();
        String[] matKhau = gson.fromJson(request.getReader(), String[].class);
        String thongBao = "Đổi mật khẩu không thành công!";
        String ma = "E";
        try {
            if (!MaHoa.chuyenDoiChuoi(matKhau[0]).equals(taiKhoan.getMatKhau())) {
                thongBao = "Mật khẩu không chính xác!";
            } else {
                taiKhoan.setMatKhau(MaHoa.chuyenDoiChuoi(matKhau[1]));
                if (taiKhoanDAO.luuTaiKHoan(taiKhoan)) {
                    thongBao = "Đổi mật khẩu thành công!";
                    ma = "OK";
                    session.removeAttribute("TENDANGNHAP");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrintWriter out = response.getWriter();
        JsonObject chuoiJson = new JsonObject();
        chuoiJson.addProperty("ma", ma);
        chuoiJson.addProperty("noiDung", thongBao);
        out.print(gson.toJson(chuoiJson));
    }
}
