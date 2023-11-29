package Main;

import tools.OrdersException;
import tools.Util;

public class Customers {

    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhone;

    public Customers(String customerID, String customerName, String customerAddress, String customerPhone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }

    public Customers() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) throws OrdersException{
        if(customerID == null || customerID.isEmpty()) {
            throw new OrdersException("Customer ID can not be empty");
        }
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) throws OrdersException {
        if(customerName == null || customerName.isEmpty()) {
            throw new OrdersException("Customer Name can not be empty");
        }
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) throws OrdersException {
        if(customerAddress == null || customerAddress.isEmpty()) {
            throw new OrdersException("Customer Address can not be empty");
        }
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) throws OrdersException {
        if(customerPhone == null || customerPhone.isEmpty()) {
            throw new OrdersException("Customer Phone can not be empty");
        }
        this.customerPhone = customerPhone;
    }

    public void update() throws OrdersException{
        String newcustomerName = Util.inputAllowEmpty("CustomerName", true);
        if (!newcustomerName.isEmpty()) {
            setCustomerName(newcustomerName);
        }
        String newcustomerAdress = Util.inputAllowEmpty("Customer Address", true);
        if (!newcustomerAdress.isEmpty()) {
            setCustomerAddress(newcustomerAdress);
        }
        String newcustomerPhone = Util.inputAllowEmpty("Customer Phone", true);
        if (!newcustomerPhone.isEmpty()) {
            setCustomerPhone(newcustomerPhone);
        }
    }
    
    
    public void input() {
        customerID = Util.inputIdWithFormat("Customer ID", "not empty, unique", checkUniqueId, "C[0-9]{3}").toUpperCase();
        customerName = Util.inputString("Customer name", "not empty").toUpperCase();
        customerAddress = Util.inputString("Customer Address", "not empty").toUpperCase();
        customerPhone = Util.inputIdWithFormat("Customer Phone", "not empty, unique", checkUniqueId, "[0-9]{10}");
    }
    
    public static Util.CheckUnique checkUniqueId = (String checkedId) -> !CustomersList.cuslist.stream().anyMatch(customer -> customer.getCustomerID().equals(checkedId));
    
    @Override
    public String toString() {
        return customerID + "," + customerName + "," + customerAddress + "," + customerPhone;
    }

}
