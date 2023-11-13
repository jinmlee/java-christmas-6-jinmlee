package christmas.view;

import christmas.enums.Event;
import christmas.enums.Menu;
import java.util.HashMap;

public class OutputView {
    public void printOrderMenu(HashMap<Menu, Integer> orderMenus) {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println("<주문 메뉴>");
        for (Menu menu : orderMenus.keySet()) {
            System.out.println(menu.getName() + " " + orderMenus.get(menu) + "개");
        }
        System.out.println();
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원\n", totalPrice);
    }

    public void printPresentMenu(boolean checkTotalPrice) {
        System.out.println("\n<증정 메뉴>");
        if (checkTotalPrice) {
            System.out.println(Menu.CHAMPAGNE.getName() + " 1개");
            return;
        }
        System.out.println("없음");
    }

    public void printApplyDiscount(HashMap<Event, Integer> applyDiscount) {
        System.out.println("\n<혜택 내역>");
        if (applyDiscount.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (Event event : applyDiscount.keySet()) {
            System.out.printf("%s: -%,d원\n", event.getName(), applyDiscount.get(event));
        }
    }

    public void printTotalBenefitAmount(int totalBenefitAmount){
        System.out.println("\n<총혜택 금액>");
        System.out.printf("%,d원\n", totalBenefitAmount);
    }

    public void printFinalTotalPrice(int finalTotalPrice){
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.printf("%,d원\n", finalTotalPrice);
    }
}
