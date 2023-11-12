package christmas.event;

import christmas.model.Receipt;

public interface EventInterface {
    int EVENT_MIN_PAYMENT = 10000;

    boolean checkApplyEvent(Receipt receipt);

    void applyEvent(Receipt receipt);
}