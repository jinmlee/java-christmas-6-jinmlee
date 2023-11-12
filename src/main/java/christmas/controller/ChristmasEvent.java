package christmas.controller;

import christmas.enums.Menu;
import christmas.model.Order;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;

public class ChristmasEvent {
    private Order order;
    private OutputView outputView = new OutputView();

    public void startEvent() {
        takeOrder();
        printOrderMenu();
        applyEvent();
    }

    public void takeOrder() {
        int date = InputView.readDate();
        HashMap<Menu, Integer> orderMenus = InputView.order();
        order = new Order(date, orderMenus);
    }

    public void printOrderMenu() {
        outputView.printOrderMenu(order.getOrderMenus());
        outputView.printTotalPrice(order.getTotalPrice());

    }

    public void applyEvent(){
        outputView.printPresentMenu(checkPresentEvent());
    }

    public boolean checkPresentEvent(){
        if(order.getTotalPrice() >= 120000){
            return true;
        }
        return false;
    }
}
