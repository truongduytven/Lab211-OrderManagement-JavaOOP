package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.Util;
import tools.OrdersException;

public class CustomersList {
        
    static ArrayList<Customers> cuslist = readfromfileCus();

    public static ArrayList<Customers> getCuslist() {
        return cuslist;
    }
    
    public static ArrayList<Customers> readfromfileCus() {
        ArrayList<Customers> newCuslist = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src\\Data\\customers.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                Customers newCustomer = new Customers();
                newCustomer.setCustomerID(txt[0]);
                newCustomer.setCustomerName(txt[1]);
                newCustomer.setCustomerAddress(txt[2]);
                newCustomer.setCustomerPhone(txt[3]);
                newCuslist.add(newCustomer);
            }
        } catch (IOException | NumberFormatException e) {
        } catch (OrdersException ex) {
            Logger.getLogger(CustomersList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newCuslist;
    }

    public static void print() {
        for (Customers customers : cuslist) {
            System.out.println(customers.toString());
        }
        System.out.println("");
    }

    public static HashMap<String, String> getMapCustomerIdToCustomerName() {
        HashMap<String, String> result = new HashMap<>();
        cuslist.forEach(o -> {
            result.put(o.getCustomerID(), o.getCustomerName());
        });
        return result;
    }

    public static Customers findCustomersById() {
        String inputId = Util.inputString("ID", "not empty");
        Customers foundCustomer = null;
        for (Customers i : cuslist) {
            if (i.getCustomerID().equals(inputId)) {
                foundCustomer = i; 
                break;
            }
        }
        if (foundCustomer == null) {
            System.out.println("\nNot found any customer have id " + inputId + "\n");
        }
        return foundCustomer;
    }

    public static void searchCustomerById() {
        Customers foundCustomer = findCustomersById();
        if (foundCustomer != null) {
            System.out.println("");
            System.out.println(foundCustomer);
            System.out.println("");
        }
    }

    public static void addCustomer() {
            Customers newCustomer = new Customers();
            newCustomer.input();
            cuslist.add(newCustomer);
            System.out.println("Add new Customer successfully");
    }

    public static void updateCustomer() throws OrdersException{
        Customers checkID = findCustomersById();
        if (checkID != null) {
            checkID.update();
            System.out.println("Updated customer!");
        }
    }

    public static void saveCustomertofile() {
        try {
            FileWriter fw = new FileWriter("src\\Data\\customers.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Customers o : cuslist) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
        }
        System.out.println("Saved  to the files");
    }
    
    public static String checkCusID(String input) {
        for (Customers customers : cuslist) {
            if (input.equals(customers.getCustomerID())) {
                return customers.getCustomerID();
            }
        }
        System.out.println("Customer ID is not available, please create a new Customer with this id.");
        String newCustomerName = Util.inputString("Customer Name", "not empty");
        String newCustomerAddress = Util.inputString("Customer Address", "not empty");
        String newCustomerPhone = Util.inputString("Customer Phone", "not empty");
        Customers newCustomers = new Customers(input, newCustomerName, newCustomerAddress, newCustomerPhone);
        cuslist.add(newCustomers);
        return newCustomers.getCustomerID();
    }
}
