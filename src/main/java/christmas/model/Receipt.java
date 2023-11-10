package christmas.model;

import christmas.enums.Menu;
import java.util.HashMap;

public class Receipt {
    private final int date;
    private final HashMap<Menu, Integer> orderMenus;

    public Receipt(int date, HashMap<Menu, Integer> orderMenus) {
        this.date = date;
        this.orderMenus = orderMenus;
    }

    public HashMap<Menu, Integer> getOrderMenus() {
        return orderMenus;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Menu menu : orderMenus.keySet()) {
            totalPrice += menu.getPrice() * orderMenus.get(menu);
        }
        return totalPrice;
    }
}
