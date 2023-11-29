package Main;

import tools.OrdersException;
import tools.Util;

public final class Orders {

    private String orderID;
    private String cusID;
    private String proID;
    private int orderQuanlity;
    private String orderDate;
    private boolean status;

    public Orders() {
    }

    public Orders(String orderID, String cusID, String proID, int oderQuanlity, String orderDate, boolean status) throws OrdersException{
        setOrderID(orderID);
        setCusID(cusID);
        setProID(proID);
        setOrderQuanlity(orderQuanlity);
        setOrderDate(orderDate);
        setStatus(status);
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) throws OrdersException{
        if(cusID == null || cusID.isEmpty()) {
            throw new OrdersException("Customer ID can not be empty");
        }
        this.cusID = cusID;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) throws OrdersException{
        if(proID == null || proID.isEmpty()) {
            throw new OrdersException("Product ID can not be empty");
        }
        this.proID = proID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) throws OrdersException{
        if(orderID == null || orderID.isEmpty()) {
            throw new OrdersException("Order ID can not be empty");
        }
        this.orderID = orderID;
    }

    public int getOrderQuanlity() {
        return orderQuanlity;
    }

    public void setOrderQuanlity(int orderQuanlity) throws OrdersException {
        if(orderQuanlity <= 0) {
            throw new OrdersException("Order quanlity must bigger 0");
        }
        this.orderQuanlity = orderQuanlity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) throws OrdersException{
        if(orderDate == null || orderDate.isEmpty()) {
            throw new OrdersException("Date can not be empty");
        }
        this.orderDate = orderDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) throws OrdersException{
        if (status != true && status != false) {
            throw new OrdersException("status must be true or false");
        }
        this.status = status;
    }

    public void update() throws OrdersException{
        String newCusID = Util.inputCustomerID("Customer ID", "not empty",true);
        if(newCusID != null) {
            setCusID(newCusID);
        }
        String newProID = Util.inputProductID("Product ID", "not empty",true);
        if(newProID != null) {
            setOrderID(newProID);
        }    
        String neworderQuanlity = Util.inputIntegerNumber("Order Quanlity", "larger 0 ", true);
        if (!neworderQuanlity.isEmpty()) {
            setOrderQuanlity(Integer.parseInt(neworderQuanlity));
        }
        String neworderDate = Util.inputDate(true);
        if (!neworderDate.isEmpty()) {
            setOrderDate(neworderDate);
        }   
        String newStatus = Util.inputStatus(true);
        if (!newStatus.isEmpty()) {
            setStatus(Boolean.parseBoolean(newStatus));
        }
    }

    @Override
    public String toString() {
        return orderID + "," + cusID + "," + proID + "," + orderQuanlity + "," + orderDate + "," + status;
    }

}
