package models;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author deadg
 */
public class SalesModel {
    private String salesNumber;
    private Date salesDate;
    private int grandTotal;
    private String paymentMethod;
    private ArrayList<SalesDetailModel> details;

    public SalesModel() {
        this.details = new ArrayList<>();
    }

    public SalesModel(String salesNumber, Date salesDate, int grandTotal, String paymentMethod) {
        this.salesNumber = salesNumber;
        this.salesDate = salesDate;
        this.grandTotal = grandTotal;
        this.paymentMethod = paymentMethod;
        this.details = new ArrayList<>();
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public ArrayList<SalesDetailModel> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<SalesDetailModel> details) {
        this.details = details;
    }

    public void addDetail(SalesDetailModel detail) {
        this.details.add(detail);
    }
}