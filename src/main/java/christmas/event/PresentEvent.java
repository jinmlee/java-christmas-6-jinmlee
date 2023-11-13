package christmas.event;

import christmas.enums.Event;
import christmas.enums.Menu;
import christmas.model.Receipt;

public class PresentEvent implements EventInterface {
    private final int PRESENT_MIN_ORDER_AMOUNT = 120000;

    @Override
    public boolean checkApplyEvent(Receipt receipt) {
        if (receipt.getTotalPrice() >= PRESENT_MIN_ORDER_AMOUNT) {
            return true;
        }
        return false;
    }

    @Override
    public void applyEvent(Receipt receipt) {
        receipt.getBenefitsDetails().put(Event.PRESENT, Menu.CHAMPAGNE.getPrice());
    }
}
