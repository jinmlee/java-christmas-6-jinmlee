package christmas.event;

import christmas.enums.Event;
import christmas.model.Receipt;

public class ChristmasDayEvent implements EventInterface {
    private final int CHRISTMAS_DAY = 25;
    private final int START_PAYBACK = 1000;
    private final int DAILY_PAYBACK = 100;
    private final int START_DATE = 1;

    @Override
    public boolean checkApplyEvent(Receipt receipt) {
        if (receipt.getDate() <= CHRISTMAS_DAY) {
            return true;
        }
        return false;
    }

    @Override
    public void applyEvent(Receipt receipt) {
        int discount = getDiscount(receipt.getDate());
        receipt.getBenefitsDetails().put(Event.CHRISTMAS_D_DAY, discount);
    }

    public int getDiscount(int date) {
        int discount = START_PAYBACK;
        discount += (date - START_DATE) * DAILY_PAYBACK;
        return discount;
    }
}
