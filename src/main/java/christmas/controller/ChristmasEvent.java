package christmas.controller;

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
        printOrder();
    }

    public void takeOrder() {
        int date = InputView.readDate();
        HashMap<Menu, Integer> orderMenus = InputView.order();
        receipt = new Receipt(date, orderMenus);
    }

    public void printOrder() {
        outputView.printOrderMenu(receipt.getOrderMenus());
        outputView.printTotalPrice(receipt.getTotalPrice());
        outputView.printPresentMenu(receipt.getTotalPrice());
    }
}
