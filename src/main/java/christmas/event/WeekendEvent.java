package christmas.event;

import christmas.enums.Day;
import christmas.enums.Event;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.model.Receipt;
import java.util.List;

public class WeekendEvent implements EventInterface {
    private final List<Day> WEEKEND = List.of(Day.FRIDAY, Day.SATURDAY);
    private final int PAYBACK = 2023;

    @Override
    public boolean checkApplyEvent(Receipt receipt) {
        if (WEEKEND.contains(receipt.getDayOfWeek())) {
            return true;
        }
        return false;
    }

    @Override
    public void applyEvent(Receipt receipt) {
        int discount = getDiscount(receipt);
        if (discount > 0) {
            receipt.getBenefitsDetails().put(Event.WEEKEND, discount);
        }
    }

    public int getDiscount(Receipt receipt) {
        int discount = 0;
        for (Menu menu : receipt.getOrderMenus().keySet()) {
            if (menu.getType().equals(MenuType.MAIN)) {
                discount += receipt.getOrderMenus().get(menu) * PAYBACK;
            }
        }
        return discount;
    }
}
