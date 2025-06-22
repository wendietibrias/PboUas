/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laporan;

import Koneksi.Koneksi;
import java.sql.*;
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
    
    public ArrayList<LaporanPenjualanModel> getLaporanPenjualanByField(int productId, java.sql.Date dateFrom, java.sql.Date dateTo) {
        ArrayList<LaporanPenjualanModel> laporanPenjualanList = new ArrayList<>();
        
        String sql = "SELECT s.sales_date as tanggal, "
                    + "s.sales_number as nomor_penjualan, "
                    + "sd.qty as total_terjual, "
                    + "sd.subtotal as subtotal, "
                    + "s.payment_method as metode_pembayaran "
                    + "FROM sales s "
                    + "JOIN sales_detail sd ON s.id = sd.sales_id "
                    + "JOIN products p ON p.id = sd.product_id "
                    + "WHERE sd.product_id = ? "
                    + "AND s.sales_date BETWEEN ? AND ? ";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.setDate(2, dateFrom);
            ps.setDate(3, dateTo);

            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                java.sql.Date tanggal = rs.getDate("tanggal");
                String nomorPenjualan = rs.getString("nomor_penjualan");
                int totalTerjual = rs.getInt("total_terjual");
                double subtotal = rs.getDouble("subtotal");
                String metodePembayaran = rs.getString("metode_pembayaran");
                
                LaporanPenjualanModel item = new LaporanPenjualanModel(tanggal, nomorPenjualan, totalTerjual, subtotal, metodePembayaran);
                laporanPenjualanList.add(item);
            }
        } catch(SQLException e){
          System.out.println(e.getMessage());
        }
         
        return laporanPenjualanList;
    }
    
    public ArrayList<LaporanPenjualanModel> getAllLaporanPenjualan(java.sql.Date dateFrom, java.sql.Date dateTo) {
        ArrayList<LaporanPenjualanModel> laporanPenjualanList = new ArrayList<>();
        
        String sql = "SELECT p.name AS product_name, "
                    + "p.sku as SKU, "
                    + "SUM(sd.qty) AS total_terjual, "
                    + "SUM(sd.subtotal) AS total_pendapatan "
                    + "FROM sales s "
                    + "JOIN sales_detail sd ON s.id = sd.sales_id "
                    + "JOIN products p ON p.id = sd.product_id "
                    + "WHERE s.sales_date BETWEEN ? AND ? "
                    + "GROUP BY p.id";
        
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, dateFrom);
            ps.setDate(2, dateTo);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                String productSKU = rs.getString("sku");
                String productName = rs.getString("product_name");
                int totalTerjual = rs.getInt("total_terjual");
                double totalPendapatan = rs.getDouble("total_pendapatan");
                
                LaporanPenjualanModel item = new LaporanPenjualanModel(productSKU, productName, totalTerjual, totalPendapatan);
                laporanPenjualanList.add(item);
            }
        } catch(SQLException e){
          System.out.println(e.getMessage());
        }
        
        return laporanPenjualanList;
    }
}
