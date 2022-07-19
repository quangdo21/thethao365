/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webthethao365.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author dovan
 */
public class KetNoiCSDL {

    protected Connection ketNoi;

    public KetNoiCSDL() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ketNoi = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=websiteTinTuc;username=sa;password=1;CharacterSet=UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
