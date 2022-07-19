/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webthethao365.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author dovan
 */
public class MaHoa {
//    Mã hóa theo MD5
    public static String chuyenDoiChuoi(String chuoi) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bamTheoBytes = md.digest(chuoi.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : bamTheoBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
