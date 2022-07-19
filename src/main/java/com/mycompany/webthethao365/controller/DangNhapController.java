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
//          /api/dangnhap
public class DangNhapController extends HttpServlet {

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
            out.println("<title>Servlet DangNhapController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DangNhapController at " + request.getContextPath() + "</h1>");
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
        TaiKhoanModel taikhoan = gson.fromJson(request.getReader(), TaiKhoanModel.class);
        TaiKhoanDAO taikhoanDAO = new TaiKhoanDAO();
        TaiKhoanModel tk = taikhoanDAO.layTaiKhoanTheoTenDangNhap(taikhoan.getTenDangNhap());
        String thongBao = "Đăng nhập không thành công!";
        String ma = "E";
        try {
            if(tk == null){
                thongBao = "Tài khoản không tồn tại!";
            } else if (!MaHoa.chuyenDoiChuoi(taikhoan.getMatKhau()).equals(tk.getMatKhau())) {
                thongBao = "Mật khẩu không chính xác!";
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("TENDANGNHAP", taikhoan.getTenDangNhap());
                thongBao = "Đăng nhập thành công!";
                ma = "OK";
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
