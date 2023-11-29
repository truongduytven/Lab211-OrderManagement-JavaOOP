package tools;

import Main.UserList;


public class Menu {

    private final MenuItem[] primaryOptions = {
        MenuItem.EXIT,
        MenuItem.ADMIN,
        MenuItem.USER
    };

    private final MenuItem[] adminOptions = {
        MenuItem.BACK,
        MenuItem.PRODUCT_LIST,
        MenuItem.CUSTOMER_LIST,
        MenuItem.CUSTOMER_SEARCH_BY_ID,
        MenuItem.CUSTOMER_ADD,
        MenuItem.CUSTOMER_UPDATE,
        MenuItem.CUSTOMER_SAVE,
        MenuItem.ORDER_SORT_BY_CUSTOMER_NAME,
        MenuItem.ORDER_LIST_ALL_PENDING_ORDERS,
        MenuItem.ORDER_ADD,
        MenuItem.ORDER_UPDATE_BASED_ON_ID,
        MenuItem.ORDER_DELETE_BASED_ON_ID,
        MenuItem.ORDER_SAVE
    };

    private final MenuItem[] userOptions = {
        MenuItem.BACK,
        MenuItem.PRODUCT_LIST,
        MenuItem.CUSTOMER_LIST,
        MenuItem.CUSTOMER_SEARCH_BY_ID,
        MenuItem.ORDER_SORT_BY_CUSTOMER_NAME,
        MenuItem.ORDER_LIST_ALL_PENDING_ORDERS,
    };

    private MenuItem primaryOption = null;
    private MenuItem subOption = null;

    public Menu() {
        this.primaryOption = MenuItem.EXIT;
        this.subOption = MenuItem.BACK;
    }

    public MenuItem getUserChoice() {
        MenuItem option = login();
        do {
            if (subOption == MenuItem.BACK) {     
                primaryOption = getChoice(option);
            }
            if (primaryOption != MenuItem.EXIT && !isRepeatAction()) {
                MenuItem newoption = login();
                subOption = getChoice(newoption);
            }
        } while (primaryOption != MenuItem.EXIT && subOption == MenuItem.BACK);
        return primaryOption.equals(MenuItem.EXIT) ? MenuItem.EXIT : subOption;
    }

    private MenuItem getChoice(MenuItem option) {
        MenuItem[] optionList = getOptionList(option);
        int numItems = showOptionMenu(option.getLabel(), optionList);
        int choice = Util.inputInteger("Please enter your choice", 0, numItems - 1);
        return optionList[choice];
    }

    private int showOptionMenu(String menuCaption, MenuItem[] optionList) {
        int numItems = 1;
        System.out.println("*********************************************");
        System.out.println(menuCaption);
        for (int i = 1; i < optionList.length; i++) {
            System.out.printf("(%d) -> %s\n", numItems, optionList[i].getLabel());
            numItems++;
        }
        System.out.printf("(0) -> %s\n", optionList[0].getLabel());
        System.out.println("*********************************************");
        return numItems;
    }

    private MenuItem[] getOptionList(MenuItem option) {
        MenuItem[] optionList;
        switch(option){
            case ADMIN : optionList = adminOptions;
                         break;
            case USER: optionList = userOptions;
                        break;
            default: optionList = primaryOptions;
                        break;
        }
        return optionList;
    }

    private boolean isRepeatAction() {
        switch (subOption) {
            case CUSTOMER_ADD:
            case CUSTOMER_UPDATE:
            case ORDER_ADD:
            case ORDER_UPDATE_BASED_ON_ID:
            case ORDER_DELETE_BASED_ON_ID:
                String confirm = Util.inputString("Repeat action (Y/N)", false);
                return confirm.trim().toLowerCase().startsWith("y");
        }
        return false;
    }
    
    private MenuItem login() {
        String userRole = UserList.UserLogin();
        if (userRole.equals("admin")) {
            return MenuItem.ADMIN;
        }
        return MenuItem.USER;
    }
}
