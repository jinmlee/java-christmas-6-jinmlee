package christmas.view;

import christmas.enums.Badge;
import christmas.enums.Event;
import christmas.enums.Menu;
import java.util.HashMap;

public class OutputView {
    private static final int MINUS = -1;
    private static final String NEW_LINE = "\n";
    private static final String NOTHING = "없음";

    public void printOrderMenu(HashMap<Menu, Integer> orderMenus) {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + NEW_LINE);
        System.out.println("<주문 메뉴>");
        for (Menu menu : orderMenus.keySet()) {
            System.out.println(menu.getName() + " " + orderMenus.get(menu) + "개");
        }
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println(NEW_LINE + "<할인 전 총주문 금액>");
        System.out.printf("%,d원" + NEW_LINE, totalPrice);
    }

    public void printPresentMenu(HashMap<Event, Integer> benefitsDetails) {
        System.out.println(NEW_LINE + "<증정 메뉴>");
        if (benefitsDetails.containsKey(Event.PRESENT)) {
            System.out.println(Menu.CHAMPAGNE.getName() + " 1개");
            return;
        }
        System.out.println(NOTHING);
    }

    public void printBenefitsDetails(HashMap<Event, Integer> benefitsDetails) {
        System.out.println(NEW_LINE + "<혜택 내역>");
        if (benefitsDetails.isEmpty()) {
            System.out.println(NOTHING);
            return;
        }
        for (Event event : benefitsDetails.keySet()) {
            System.out.printf("%s: %,d원" + NEW_LINE, event.getName(), benefitsDetails.get(event) * MINUS);
        }
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println(NEW_LINE + "<총혜택 금액>");
        System.out.printf("%,d원" + NEW_LINE, totalBenefitAmount * MINUS);
    }

    public void printFinalTotalPrice(int finalTotalPrice) {
        System.out.println(NEW_LINE + "<할인 후 예상 결제 금액>");
        System.out.printf("%,d원" + NEW_LINE, finalTotalPrice);
    }

    public void printBadge(Badge badge) {
        System.out.println(NEW_LINE + "<12월 이벤트 배지>");
        System.out.println(badge.getName());
    }
}
