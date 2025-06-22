/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author deadg
 */
public class LaporanPenjualanModel {
    private String productSKU;
    private String productName;
    private int totalTerjual;
    private double totalPendapatan;
    
    private java.sql.Date tanggal;
    private String nomorPenjualan;
    private double subtotal;
    private String metodePembayaran;
    
    public LaporanPenjualanModel(java.sql.Date tanggal, String nomorPenjualan, int totalTerjual, double subtotal, String metodePembayaran) {
        this.tanggal = tanggal;
        this.nomorPenjualan = nomorPenjualan;
        this.totalTerjual = totalTerjual;
        this.subtotal = subtotal;
        this.metodePembayaran = metodePembayaran;
    }

    public LaporanPenjualanModel(String productSKU, String productName, int totalTerjual, double totalPendapatan) {
        this.productSKU = productSKU;
        this.productName = productName;
        this.totalTerjual = totalTerjual;
        this.totalPendapatan = totalPendapatan;
    }
    
    public String getProductSKU() {
        return productSKU;
    }
    
    public void setProductSKU(String productSKU) {
        this.productSKU = productSKU;
    }

    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getTotalTerjual() {
        return totalTerjual;
    }
    
    public void setTotalTerjual(int totalTerjual) {
        this.totalTerjual = totalTerjual;
    }

    public double getTotalPendapatan() {
        return totalPendapatan;
    }
    
    public void setTotalPendapatan(double totalPendapatan) {
        this.totalPendapatan = totalPendapatan;
    }
    
    public java.sql.Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(java.sql.Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getNomorPenjualan() {
        return nomorPenjualan;
    }

    public void setNomorPenjualan(String nomorPenjualan) {
        this.nomorPenjualan = nomorPenjualan;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    public String getMetodePembayaran() {
        return metodePembayaran;
    }
    
    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }
}
