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
        for (Day day : Day.values()) {
            if (day.getDayOfWeek() == date % 7) {
                return day;
            }
        }
        return null;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Menu menu : orderMenus.keySet()) {
            totalPrice += menu.getPrice() * orderMenus.get(menu);
        }
        return totalPrice;
    }
}
