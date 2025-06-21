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
    private String productName;
    private int totalTerjual;
    private double totalPendapatan;

    public LaporanPenjualanModel(String productName, int totalTerjual, double totalPendapatan) {
        this.productName = productName;
        this.totalTerjual = totalTerjual;
        this.totalPendapatan = totalPendapatan;
    }

    public String getProductName() {
        return productName;
    }

    public int getTotalTerjual() {
        return totalTerjual;
    }

    public double getTotalPendapatan() {
        return totalPendapatan;
    }
}
