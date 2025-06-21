/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laporan;

import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.LaporanPenjualanModel;

/**
 *
 * @author deadg
 */
public class LaporanPenjualanController {
    public Koneksi koneksiInit;
    public Connection conn;
    
    public LaporanPenjualanController() {
        try {
           koneksiInit = new Koneksi();
           conn = koneksiInit.connection;
        } catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<LaporanPenjualanModel> renderLaporanPenjualan(int productId, java.sql.Date dateFrom, java.sql.Date dateTo) {
        ArrayList<LaporanPenjualanModel> laporanPenjualanList = new ArrayList<>();
        
        try {
            String getLaporanPenjualan = "SELECT p.name AS product_name, "
                                    + "SUM(sd.qty) AS total_terjual, "
                                    + "SUM(sd.subtotal) AS total_pendapatan "
                                    + "FROM sales s "
                                    + "JOIN sales_detail sd ON s.id = sd.sales_id "
                                    + "JOIN products p ON p.id = sd.product_id "
                                    + "WHERE sd.product_id = " + productId + " "
                                    + "AND s.sales_date BETWEEN '" + dateFrom + "' AND '" + dateTo + "' "
                                    + "GROUP BY p.id";
            
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(getLaporanPenjualan);
            
            while(rs.next()) {
                String productName = rs.getString("product_name");
                int totalTerjual = rs.getInt("total_terjual");
                double totalPendapatan = rs.getDouble("total_pendapatan");
                
                LaporanPenjualanModel item = new LaporanPenjualanModel(productName, totalTerjual, totalPendapatan);
                laporanPenjualanList.add(item);
            }
        } catch(SQLException e){
          System.out.println(e.getMessage());
        }
         
        return laporanPenjualanList;
    }
    
    public ArrayList<LaporanPenjualanModel> renderAllPenjualan(java.sql.Date dateFrom, java.sql.Date dateTo) {
        ArrayList<LaporanPenjualanModel> laporanPenjualanList = new ArrayList<>();
        
        try {
            String getLaporanPenjualan = "SELECT p.name AS product_name, "
                                    + "SUM(sd.qty) AS total_terjual, "
                                    + "SUM(sd.subtotal) AS total_pendapatan "
                                    + "FROM sales s "
                                    + "JOIN sales_detail sd ON s.id = sd.sales_id "
                                    + "JOIN products p ON p.id = sd.product_id "
                                    + "AND s.sales_date BETWEEN '" + dateFrom + "' AND '" + dateTo + "' "
                                    + "GROUP BY p.id";
            
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(getLaporanPenjualan);
            
            while(rs.next()) {
                String productName = rs.getString("product_name");
                int totalTerjual = rs.getInt("total_terjual");
                double totalPendapatan = rs.getDouble("total_pendapatan");
                
                LaporanPenjualanModel item = new LaporanPenjualanModel(productName, totalTerjual, totalPendapatan);
                laporanPenjualanList.add(item);
            }
        } catch(SQLException e){
          System.out.println(e.getMessage());
        }
        
        return laporanPenjualanList;
    }
}
