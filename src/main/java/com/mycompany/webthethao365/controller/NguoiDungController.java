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
import com.mycompany.webthethao365.dao.TinTucDAO;
import com.mycompany.webthethao365.model.NguoiDungModel;
import com.mycompany.webthethao365.model.TaiKhoanModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dovan
 */
//          /api/nguoidung
public class NguoiDungController extends HttpServlet {

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
            out.println("<title>Servlet NguoiDungController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NguoiDungController at " + request.getContextPath() + "</h1>");
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
    
//Lấy thông tin người dùng
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        TaiKhoanModel taiKhoan = taiKhoanDAO.layTaiKhoanTheoTenDangNhap((String) session.getAttribute("TENDANGNHAP"));
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
        NguoiDungModel nguoiDung = nguoiDungDAO.layNguoiDungTheoId(taiKhoan.getId());
        Gson gson = new Gson();

        PrintWriter out = response.getWriter();
        out.print(gson.toJson(nguoiDung));
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
    
//Sửa thông tin người dùng
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String thongBao = "Cập nhật thông tin không thành công!";
        String ma = "E";
        JsonObject chuoiJson = new JsonObject();
        try {
            HttpSession session = request.getSession();
            NguoiDungModel nguoiDung = gson.fromJson(request.getReader(), NguoiDungModel.class);
            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
            TaiKhoanModel taiKhoan = taiKhoanDAO.layTaiKhoanTheoTenDangNhap((String) session.getAttribute("TENDANGNHAP"));
            nguoiDung.setId(taiKhoan.getId());
            NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
            nguoiDung = nguoiDungDAO.luuNguoiDung(nguoiDung);
            thongBao = "Cập nhật thông tin thành công!";
            ma = "OK";
            chuoiJson.addProperty("dulieu", gson.toJson(nguoiDung));
        } catch (Exception e) {
            System.out.println(e);
        }
        PrintWriter out = response.getWriter();
        chuoiJson.addProperty("ma", ma);
        chuoiJson.addProperty("noiDung", thongBao);
        out.print(chuoiJson);
    }
}
