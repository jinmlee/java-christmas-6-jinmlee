package christmas.model;

import christmas.enums.Event;
import christmas.enums.Menu;
import java.util.HashMap;

public class Receipt extends Order {
    public static final int PRESENT_MIN_PAYMENT = 120000;

    private HashMap<Event, Integer> applyDiscount = new HashMap<>();

    public Receipt(int date, HashMap<Menu, Integer> orderMenus) {
        super(date, orderMenus);
    }

    public HashMap<Event, Integer> getApplyDiscount() {
        return applyDiscount;
    }

    public boolean applyPresentEvent() {
        if (getTotalPrice() >= PRESENT_MIN_PAYMENT) {
            applyDiscount.put(Event.PRESENT, Menu.CHAMPAGNE.getPrice());
            return true;
        }
        return false;
    }

    public int getTotalBenefitAmount() {
        int totalBenefitAmount = 0;
        for (int benefitAmount : applyDiscount.values()) {
            totalBenefitAmount += benefitAmount;
        }
        totalBenefitAmount *= -1;
        return totalBenefitAmount;
    }

    public int getFinalTotalPrice() {
        int totalBenefitAmount = getTotalBenefitAmount();
        if(applyPresentEvent()){
            totalBenefitAmount += Menu.CHAMPAGNE.getPrice();
        }
        return getTotalPrice() + totalBenefitAmount;
    }
}
