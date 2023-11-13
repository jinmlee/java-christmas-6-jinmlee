package christmas.model;

import christmas.enums.Badge;
import christmas.enums.Event;
import christmas.enums.Menu;
import java.util.HashMap;

public class Receipt extends Order {
    private HashMap<Event, Integer> benefitsDetails = new HashMap<>();

    public Receipt(int date, HashMap<Menu, Integer> orderMenus) {
        super(date, orderMenus);
    }

    public HashMap<Event, Integer> getBenefitsDetails() {
        return benefitsDetails;
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
        if (benefitsDetails.containsKey(Event.PRESENT)) {
            totalBenefitAmount -= Menu.CHAMPAGNE.getPrice();
        }
        return getTotalPrice() - totalBenefitAmount;
    }

    public Badge getBadge() {
        return Badge.getGiftBadge(getTotalBenefitAmount());
    }
}
