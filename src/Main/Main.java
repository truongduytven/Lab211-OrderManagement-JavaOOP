package Main;

import tools.Menu;
import tools.MenuItem;
import tools.OrdersException;

public class Main {
    private void process() throws OrdersException{
        Menu menu = new Menu();
        int option = Integer.MAX_VALUE;
        MenuItem userChoice;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case PRODUCT_LIST:
                    ProductList.print();
                    break;
                case CUSTOMER_LIST:
                    CustomersList.print();
                    break;
                case CUSTOMER_SEARCH_BY_ID:
                    CustomersList.searchCustomerById();
                    break;
                case CUSTOMER_ADD:
                    CustomersList.addCustomer();
                    break;
                case CUSTOMER_UPDATE:
                    CustomersList.updateCustomer();
                    break;
                case CUSTOMER_SAVE:
                    CustomersList.saveCustomertofile();
                    break;
                case ORDER_SORT_BY_CUSTOMER_NAME:
                    OrdersList.sortOrder();
                    break;
                case ORDER_LIST_ALL_PENDING_ORDERS:
                    OrdersList.printPendingOrder();
                    break;
                case ORDER_ADD:
                    OrdersList.addOrder();
                    break;
                case ORDER_UPDATE_BASED_ON_ID:
                    OrdersList.updateOrders();
                    break;
                case ORDER_DELETE_BASED_ON_ID:
                    OrdersList.removeOrders();
                case ORDER_SAVE:
                    OrdersList.saveOrdertofile();
                case EXIT:
                    System.out.println("Exited!");
                    break;
                default:
                    System.out.println("???");
            }
        } while (userChoice != MenuItem.EXIT);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws OrdersException {
        new Main().process();
    }
}
