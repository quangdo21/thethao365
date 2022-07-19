/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webthethao365.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycompany.webthethao365.dao.TaiKhoanDAO;
import com.mycompany.webthethao365.dao.TinTucDAO;
import com.mycompany.webthethao365.model.TaiKhoanModel;
import com.mycompany.webthethao365.model.TinTucModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dovan
 */
public class TinTucController extends HttpServlet {

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
            out.println("<title>Servlet TinTucController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TinTucController at " + request.getContextPath() + "</h1>");
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
//          /api/tintuc
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        TinTucDAO dao = new TinTucDAO();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
//        Lấy tin tức theo id
//        /api/tintuc?id=...
        if (request.getParameter("id") != null) {
            TinTucModel tin = dao.layTinTucTheoId(Integer.parseInt(request.getParameter("id")));
            out.print(gson.toJson(tin));
        }
//        Lấy danh sách tin tức
//        /api/tintuc?danhsach=...
        if (request.getParameter("danhsach") != null) {
//            10 tin mới nhất
            if (request.getParameter("danhsach").equals("10")) {
                List<TinTucModel> danhSachTin = dao.lay10TinMoi();
                out.print(gson.toJson(danhSachTin));
            } else {
//                tất cả
                List<TinTucModel> danhSachTin = dao.layTatCaTinTuc();
                out.print(gson.toJson(danhSachTin));
            }
        }
//        Lấy tin tức mới nhất theo thể loại
//        /api/tintuc?hot1id=...
        if (request.getParameter("hot1id") != null) {
            TinTucModel tinhot = dao.layTinTucHot1(Integer.parseInt(request.getParameter("hot1id")));
            out.print(gson.toJson(tinhot));
        }
//        Lấy danh sách 3 tin tức mới tiếp theo theo thể loại
//        /api/tintuc?hot3id=...
        if (request.getParameter("hot3id") != null) {
            List<TinTucModel> tin3hot = dao.layTinTucHot3(Integer.parseInt(request.getParameter("hot3id")));
            out.print(gson.toJson(tin3hot));
        }
//        Lấy danh sách tất cả tin tức theo thể loại
//        /api/tintuc?theloai=...
        if (request.getParameter("theloai") != null) {
            List<TinTucModel> tin = dao.layTinTucTheoTheLoai(Integer.parseInt(request.getParameter("theloai")));
            out.print(gson.toJson(tin));
        }
//        Tìm kiếm tin tức
        if (request.getParameter("timkiem") != null) {
            List<TinTucModel> danhSachTin = dao.layTinTucTheoTieuDe(request.getParameter("timkiem"));
            out.print(gson.toJson(danhSachTin));
        }
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
        String thongBao = "Lưu tin không thành công!";
        String ma = "E";
        JsonObject chuoiJson = new JsonObject();
        try {
            TinTucModel tinTuc = new TinTucModel();
            tinTuc = gson.fromJson(request.getReader(), TinTucModel.class);
            HttpSession session = request.getSession();
            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
            TaiKhoanModel taiKhoan = taiKhoanDAO.layTaiKhoanTheoTenDangNhap((String) session.getAttribute("TENDANGNHAP"));
            tinTuc.setTaiKhoanId(taiKhoan.getId());
            tinTuc.setNoiDung(tinTuc.getNoiDung().replaceAll("\n\n", "\n").replaceAll("\n", "<br>"));
            TinTucDAO tinTucDAO = new TinTucDAO();
            System.out.println(tinTuc.toString());
            tinTucDAO.luuTinTuc(tinTuc);
            thongBao = "Lưu tin thành công!";
            ma = "OK";
        } catch (Exception e) {
            System.out.println(e);
        }
        PrintWriter out = response.getWriter();
        chuoiJson.addProperty("ma", ma);
        chuoiJson.addProperty("noiDung", thongBao);
        out.print(chuoiJson);
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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String thongBao = "Xóa không thành công!";
        String ma = "E";
        JsonObject chuoiJson = new JsonObject();
        if (request.getParameter("id") != null) {
            TinTucDAO tinTucDAO = new TinTucDAO();
            tinTucDAO.xoaTinTuc(Integer.parseInt(request.getParameter("id")));
            ma = "OK";
            thongBao = "Xóa tin thành công!";
        }
        PrintWriter out = response.getWriter();
        chuoiJson.addProperty("ma", ma);
        chuoiJson.addProperty("noiDung", thongBao);
        out.print(chuoiJson);
    }

}
