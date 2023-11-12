package christmas.controller;

import christmas.enums.Event;
import christmas.enums.Menu;
import christmas.model.Receipt;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;

public class ChristmasEvent {
    private Receipt receipt;
    private OutputView outputView = new OutputView();

    public void startEvent() {
        takeOrder();
        printOrderMenu();
        applyEvent();
    }

    public void takeOrder() {
        int date = InputView.readDate();
        HashMap<Menu, Integer> orderMenus = InputView.order();
        receipt = new Receipt(date, orderMenus);
    }

    public void printOrderMenu() {
        outputView.printOrderMenu(receipt.getOrderMenus());
        outputView.printTotalPrice(receipt.getTotalPrice());
    }

    public void applyEvent() {
        outputView.printPresentMenu(receipt.applyPresentEvent());
        if (Event.WEEKDAY.getDays().contains(receipt.getDayOfWeek())) {
            receipt.applyWeekDayDiscount();
        }
        if (Event.WEEKEND.getDays().contains(receipt.getDayOfWeek())) {
            receipt.applyWeekendDiscount();
        }


        outputView.printApplyDiscount(receipt.getApplyDiscount());
    }
}
