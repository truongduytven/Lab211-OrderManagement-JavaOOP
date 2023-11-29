package Main;

import tools.OrdersException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import tools.Util;


public class OrdersList {

    private static ArrayList<Orders> ordlist = readfromfileOrder();

    public static ArrayList<Orders> readfromfileOrder() {
        ArrayList<Orders> orderList = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src\\Data\\orders.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                try {
                if(txt.length != 6) {
                    throw new Exception();
                }     
                Orders neworder = new Orders();
                neworder.setOrderID(txt[0].trim());
                neworder.setCusID(txt[1].trim());
                neworder.setProID(txt[2].trim());
                neworder.setOrderQuanlity(Integer.parseInt(txt[3].trim()));
                neworder.setOrderDate(txt[4].trim());
                neworder.setStatus(Boolean.parseBoolean(txt[5].trim()));
                orderList.add(neworder);
                } catch (Exception e) {
                }
            }
            br.close();
            fr.close();
        } catch (IOException | NumberFormatException e) {
        }
        return orderList;
    }

    public static void sortOrder() {
        HashMap<String, String> CustomerIdToCustomerName = CustomersList.getMapCustomerIdToCustomerName();
        Collections.sort(ordlist, new CustomerNameComparator());
        for (Orders orders : ordlist) {
            System.out.println(orders.toString());
        }
        System.out.println("");
    }

    public static class CustomerNameComparator implements Comparator<Orders> {

        @Override
        public int compare(Orders cus1, Orders cus2) {
            HashMap<String, String> CustomerIdtoCustomerName = CustomersList.getMapCustomerIdToCustomerName();
            String customerName1 = CustomerIdtoCustomerName.get(cus1.getCusID());
            String customerName2 = CustomerIdtoCustomerName.get(cus2.getCusID());
            return customerName1.compareTo(customerName2);
        }
    }

    public static void printPendingOrder() {
        ordlist.forEach(o -> {
            if (o.isStatus() == false) {
                System.out.println(o.toString());
            }
        });
    }

    public static void addOrder() throws OrdersException{
            Orders newOrders = new Orders();
            newOrders.setOrderID(Util.inputIdWithFormat("Order ID", "not empty, unique", checkUniqueId, "D[0-9]{3}"));
            newOrders.setCusID(Util.inputCustomerID("Customer ID", "not empty",false));
            newOrders.setProID(Util.inputProductID("Product ID", "not empty",false));
            newOrders.setOrderQuanlity(Integer.parseInt(Util.inputIntegerNumber("Order Quanlity", "greater than 0", false)));
            newOrders.setOrderDate(Util.inputDate(false));
            newOrders.setStatus(Boolean.getBoolean(Util.inputStatus(false)));
            ordlist.add(newOrders);
            System.out.println("Add new Order successfully");
    }
    public static Util.CheckUnique checkUniqueId = new Util.CheckUnique() {
        @Override
        public boolean check(String checkedId) {
            return !ordlist.stream().anyMatch(Orders -> Orders.getOrderID().equals(checkedId));
        }
    };

    public static void saveOrdertofile() {
        try {
            FileWriter fw = new FileWriter("src\\Data\\orders.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Orders o : ordlist) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
        System.out.println("Saved  to the files");
    }

    public static Orders findOrdersById() {
        String inputId = Util.inputString("ID", "not empty");
        Orders foundOrders = null;
        for (Orders i : ordlist) {
            if (i.getOrderID().equals(inputId)) {
                foundOrders = i; 
                break;
            }
        }
        if (foundOrders == null) {
            System.out.println("\nNot found any order have id " + inputId + "\n");
        }
        return foundOrders;
    }

    public static void updateOrders() throws OrdersException {
        Orders checkID = findOrdersById();
        if (checkID != null) {
            checkID.update();
            System.out.println("Updated order!");
        }
    }

    public static void removeOrders() {
        Orders checkID = findOrdersById();
        if (checkID != null) {
            ordlist.remove(checkID);
            System.out.println("Removed order!");
        }
    }
}
