package christmas.model;

import christmas.enums.Day;
import christmas.enums.Menu;
import java.util.HashMap;

public class Order {
    private final int date;
    private final HashMap<Menu, Integer> orderMenus;

    public Order(int date, HashMap<Menu, Integer> orderMenus) {
        this.date = date;
        this.orderMenus = orderMenus;
    }

    public HashMap<Menu, Integer> getOrderMenus() {
        return orderMenus;
    }

    public int getDate() {
        return date;
    }

    public Day getDayOfWeek() {
        return Day.getVisitDayOfWeek(date);
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Menu menu : orderMenus.keySet()) {
            totalPrice += menu.getPrice() * orderMenus.get(menu);
        }
        return totalPrice;
    }
}
