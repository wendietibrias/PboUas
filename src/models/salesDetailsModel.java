/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Asus
 */
public class salesDetailsModel {
    public int salesId;
    public int productId;
    public int qty;
    public int subtotal;
    public int grandtotal;
    public int id;
    public String productName;
    public String productSku;
    public int productPrice;
    
    
    public void setSalesDetail(
       int salesId,
       int productId,
       int qty,
       int subtotal,
       int grandtotal,
       int id,
       int productPrice,
       String productName,
       String productSku
    ){
       this.salesId = salesId;
       this.productId = productId;
       this.qty = qty;
       this.subtotal = subtotal;
       this.grandtotal = grandtotal;
       this.id = id;
       this.productPrice = productPrice;
       this.productName = productName;
       this.productSku = productSku;
    }
    
    public void createSalesDetail(
       int salesId,
       int productId,
       int qty,
       int subtotal,
       int grandtotal
    ){
       this.salesId = salesId;
       this.productId = productId;
       this.qty = qty;
       this.subtotal = subtotal;
       this.grandtotal = grandtotal;
    }
}
