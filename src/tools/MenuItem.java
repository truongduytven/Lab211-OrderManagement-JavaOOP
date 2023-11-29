package tools;


public enum MenuItem {

    MAIN_OPTIONS("Order Manager"),
    BACK("Back"),
    EXIT("Exit"),
    ADMIN("Admin"),
    PRODUCT_LIST("Product List"),
    CUSTOMER_LIST("Customer List"),
    CUSTOMER_SEARCH_BY_ID("Search Customer"),
    CUSTOMER_ADD("Add customer"),
    CUSTOMER_UPDATE("Update customer"),
    CUSTOMER_SAVE("Save customer"),
    ORDER_SORT_BY_CUSTOMER_NAME("Sort order"),
    ORDER_LIST_ALL_PENDING_ORDERS("Pending orders"),
    ORDER_ADD("Add order"),
    ORDER_UPDATE_BASED_ON_ID("Update order"),
    ORDER_DELETE_BASED_ON_ID("Delete order"),
    ORDER_SAVE("Save order"),
    USER("User"),
    ;
    private MenuItem() {
        this.label = null;
    }

    private final String label;

    public String getLabel() {
        return label;
    }

    private MenuItem(String label) {
        this.label = label;
    }

}
