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

    public void applyEvent(){
        outputView.printPresentMenu(checkPresentEvent());
    }

    public boolean checkPresentEvent(){
        if(receipt.getTotalPrice() >= 120000){
            return true;
        }
        return false;
    }
}
