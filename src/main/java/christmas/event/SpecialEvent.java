package christmas.event;

import christmas.enums.Event;
import christmas.model.Receipt;
import java.util.List;

public class SpecialEvent implements EventInterface {
    private final List<Integer> DAYS = List.of(3, 10, 17, 24, 25, 31);
    private final int PAYBACK = 1000;

    @Override
    public boolean checkApplyEvent(Receipt receipt) {
        if (receipt.getTotalPrice() >= EVENT_MIN_PAYMENT && DAYS.contains(receipt.getDate())) {
            return true;
        }
        return false;
    }

    @Override
    public void applyEvent(Receipt receipt) {
        receipt.getBenefitsDetails().put(Event.SPECIAL, PAYBACK);
    }
}
