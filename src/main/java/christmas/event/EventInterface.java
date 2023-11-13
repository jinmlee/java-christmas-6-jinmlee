package christmas.event;

import christmas.model.Receipt;

public interface EventInterface {
    boolean checkApplyEvent(Receipt receipt);

    void applyEvent(Receipt receipt);
}