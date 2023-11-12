package christmas.model;

import christmas.enums.Event;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import java.util.HashMap;

public class Receipt extends Order {
    public static final int PRESENT_MIN_PAYMENT = 120000;
    public static final int EVENT_MIN_PAYMENT = 10000;

    private HashMap<Event, Integer> applyDiscount = new HashMap<>();

    public Receipt(int date, HashMap<Menu, Integer> orderMenus) {
        super(date, orderMenus);
    }

    public HashMap<Event, Integer> getApplyDiscount() {
        return applyDiscount;
    }

    public boolean applyPresentEvent() {
        if (getTotalPrice() >= PRESENT_MIN_PAYMENT) {
            return true;
        }
        return false;
    }

    public void applyWeekDayDiscount() {
        int discount = 0;
        for (Menu menu : getOrderMenus().keySet()) {
            if (menu.getType().equals(MenuType.DESSERT)) {
                discount += Event.WEEKDAY.getPayback(getOrderMenus().get(menu));
            }
        }
        addApplyDiscount(Event.WEEKDAY, discount);
    }

    public void applyWeekendDiscount() {
        int discount = 0;
        for (Menu menu : getOrderMenus().keySet()) {
            if (menu.getType().equals(MenuType.MAIN)) {
                discount += Event.WEEKEND.getPayback(getOrderMenus().get(menu));
            }
        }
        addApplyDiscount(Event.WEEKEND, discount);
    }

    public void addApplyDiscount(Event event, int discount){
        if(getTotalPrice() >= EVENT_MIN_PAYMENT && discount > 0){
            applyDiscount.put(event, discount);
        }
    }
}
