package christmas.controller;

import christmas.view.InputView;

public class ChristmasEvent {
    public void startEvent() {
        InputView.readDate();
        InputView.order();
    }
}
