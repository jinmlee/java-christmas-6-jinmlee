package christmas.event;

import christmas.enums.Day;
import christmas.enums.Event;
import christmas.enums.Menu;
import christmas.enums.MenuType;
import christmas.model.Receipt;
import java.util.List;

public class WeekdayEvent implements EventInterface {
    private static final List<Day> WEEKDAY =
            List.of(Day.SUNDAY, Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY);
    private static final int WEEKDAY_PAYBACK = 2023;

    @Override
    public boolean checkApplyEvent(Receipt receipt) {
        if (WEEKDAY.contains(receipt.getDayOfWeek())) {
            return true;
        }
        return false;
    }

    @Override
    public void applyEvent(Receipt receipt) {
        int discount = getDiscount(receipt);
        if (discount > 0) {
            receipt.getBenefitsDetails().put(Event.WEEKDAY, discount);
        }
    }

    public int getDiscount(Receipt receipt) {
        int discount = 0;
        for (Menu menu : receipt.getOrderMenus().keySet()) {
            if (menu.getType().equals(MenuType.DESSERT)) {
                discount += receipt.getOrderMenus().get(menu) * WEEKDAY_PAYBACK;
            }
        }
        return discount;
    }
}
