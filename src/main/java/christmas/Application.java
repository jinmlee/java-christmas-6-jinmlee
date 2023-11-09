package christmas;

import christmas.controller.ChristmasEvent;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ChristmasEvent christmasEvent = new ChristmasEvent();
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        christmasEvent.startOrder();
    }
}
