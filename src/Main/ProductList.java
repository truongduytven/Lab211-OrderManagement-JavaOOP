package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProductList {

    public static ArrayList<Products> prolist = readfromfilePro();

    public static ArrayList<Products> readfromfilePro() {
        ArrayList<Products> newprolist = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src\\Data\\products.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                Products newProducts = new Products();
                newProducts.setProductID(txt[0]);
                newProducts.setProductName(txt[1]);
                newProducts.setUnit(txt[2]);
                newProducts.setOrigin(txt[3]);
                newProducts.setPrice(Integer.parseInt(txt[4]));
                newprolist.add(newProducts);

            }
        } catch (IOException | NumberFormatException e) {
        }
        return newprolist;
    }

    public static void print() {
        if (prolist.size() > 0) {
            prolist.forEach((products) -> {
                System.out.println(products.toString());
            });
        }
    }
    
    public static String checkProID(String input) {
        for (Products products : prolist) {
            if (input.equals(products.getProductID())) {
                return products.getProductID();
            }
        }
        return "notAvaiable";
    }
}
