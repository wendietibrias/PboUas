package models;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author deadg
 */

public class SalesModel {
    public String salesNumber;
    public int grandtotal;
    public int subtotal;
    public java.util.Date salesDate;
    public java.sql.Date salesDateSql;
    public int warehouseId;
    public String warehouseName;
    public int id;
    public String paymentMethod;
    public ArrayList<SalesDetailModel> details;
    
    public SalesModel() {
        this.details = new ArrayList<>();
    }

    public SalesModel(String salesNumber, java.sql.Date salesDateSql, int grandtotal, String paymentMethod) {
        this.salesNumber = salesNumber;
        this.salesDateSql = salesDateSql;
        this.grandtotal = grandtotal;
        this.paymentMethod = paymentMethod;
        this.details = new ArrayList<>();
    }
    
    public void createSalesInit(String salesNumber,java.util.Date salesDate,int warehouseId){
        this.salesNumber = salesNumber;
        this.salesDate = salesDate;
        this.warehouseId = warehouseId;
    }
    
    public void createSales(
       String salesNumber,
       int grandtotal,
       int subtotal,
       int warehouseId,
       Date salesDate
    ){
        this.salesNumber = salesNumber;
        this.subtotal = subtotal;
        this.grandtotal = grandtotal;
        this.warehouseId = warehouseId;
        this.salesDate = salesDate;
    }
    
    public void setSales(
       String salesNumber,
       int subtotal,
       int grandtotal,
       int warehouseId,
       String warehouseName,
       Date salesDate,
       int id
    ){
       this.salesNumber = salesNumber;
       this.subtotal = subtotal;
       this.grandtotal = grandtotal;
       this.warehouseName = warehouseName;
       this.salesDate = salesDate;
       this.warehouseId = warehouseId;
       this.id = id;
    }
}