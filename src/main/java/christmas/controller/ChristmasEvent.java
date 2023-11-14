package christmas.controller;

import christmas.enums.Menu;
import christmas.event.ChristmasDayEvent;
import christmas.event.EventInterface;
import christmas.event.PresentEvent;
import christmas.event.SpecialEvent;
import christmas.event.WeekdayEvent;
import christmas.event.WeekendEvent;
import christmas.model.Receipt;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;
import java.util.List;

public class ChristmasEvent {
    private static final int EVENT_MiN_ORDER_AMOUNT = 10000;
    private static final List<EventInterface> EVENTS = List.of(
            new WeekdayEvent(), new WeekendEvent(), new ChristmasDayEvent(),
            new SpecialEvent(), new PresentEvent());

    private Receipt receipt;
    private OutputView outputView = new OutputView();

    public void startEvent() {
        takeOrder();
        printOrder();
        if (receipt.getTotalPrice() >= EVENT_MiN_ORDER_AMOUNT) {
            applyEvent();
        }
        printApplyEvent();
    }

    public void takeOrder() {
        int date = InputView.readDate();
        HashMap<Menu, Integer> orderMenus = InputView.order();
        receipt = new Receipt(date, orderMenus);
    }

    public void printOrder() {
        outputView.printOrderMenu(receipt.getOrderMenus());
        outputView.printTotalPrice(receipt.getTotalPrice());
    }

    public void applyEvent() {
        for (EventInterface event : EVENTS) {
            if (event.checkApplyEvent(receipt)) {
                event.applyEvent(receipt);
            }
        }
    }

    public void printApplyEvent() {
        outputView.printPresentMenu(receipt.getBenefitsDetails());
        outputView.printBenefitsDetails(receipt.getBenefitsDetails());
        outputView.printTotalBenefitAmount(receipt.getTotalBenefitAmount());
        outputView.printFinalTotalPrice(receipt.getFinalTotalPrice());
        outputView.printBadge(receipt.getBadge());
    }
}
