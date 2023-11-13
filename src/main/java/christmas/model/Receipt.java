package christmas.model;

import christmas.enums.Badge;
import christmas.enums.Event;
import christmas.enums.Menu;
import java.util.HashMap;

public class Receipt extends Order {
    public static final int PRESENT_MIN_PAYMENT = 120000;

    private HashMap<Event, Integer> benefitsDetails = new HashMap<>();

    public Receipt(int date, HashMap<Menu, Integer> orderMenus) {
        super(date, orderMenus);
    }

    public HashMap<Event, Integer> getBenefitsDetails() {
        return benefitsDetails;
    }

    public boolean applyPresentEvent() {
        if (getTotalPrice() >= PRESENT_MIN_PAYMENT) {
            benefitsDetails.put(Event.PRESENT, Menu.CHAMPAGNE.getPrice());
            return true;
        }
        return false;
    }

    public int getTotalBenefitAmount() {
        int totalBenefitAmount = 0;
        for (int benefitAmount : benefitsDetails.values()) {
            totalBenefitAmount += benefitAmount;
        }
        return totalBenefitAmount;
    }

    public int getFinalTotalPrice() {
        int totalBenefitAmount = getTotalBenefitAmount();
        if (applyPresentEvent()) {
            totalBenefitAmount -= Menu.CHAMPAGNE.getPrice();
        }
        return getTotalPrice() - totalBenefitAmount;
    }

    public Badge getBadge() {
        for (Badge badge : Badge.values()) {
            if (getTotalBenefitAmount() >= badge.getMinBenefitAmount()) {
                return badge;
            }
        }
        return Badge.NOTHING;
    }
}
