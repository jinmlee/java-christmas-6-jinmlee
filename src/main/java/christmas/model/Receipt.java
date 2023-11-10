package christmas.model;

import java.util.HashMap;

public class Receipt {
    private final int date;
    private final HashMap<String, Integer> orderMenus;

    public Receipt(int date, HashMap<String, Integer> orderMenus) {
        this.date = date;
        this.orderMenus = orderMenus;
    }

    public HashMap<String, Integer> getOrderMenus() {
        return orderMenus;
    }
}
