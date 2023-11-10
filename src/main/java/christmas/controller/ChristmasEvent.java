package christmas.controller;

import christmas.model.Receipt;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;

public class ChristmasEvent {
    private Receipt receipt;
    private OutputView outputView = new OutputView();

    public void startEvent() {
        takeOrder();
        outputView.printOrderMenu(receipt.getOrderMenus());
    }

    public void takeOrder() {
        int date = InputView.readDate();
        HashMap<String, Integer> orderMenus = InputView.order();
        receipt = new Receipt(date, orderMenus);
    }
}
