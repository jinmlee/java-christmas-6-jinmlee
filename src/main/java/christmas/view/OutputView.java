package christmas.view;

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

    public void printTotalPrice(int totalPrice){
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(totalPrice + "원");
        System.out.println();
    }

    public void printPresentMenu(boolean checkTotalPrice){
        System.out.println("<증정 메뉴>");
        if(checkTotalPrice){
            System.out.println("샴페인 1개");
        }
        if(checkTotalPrice){
            System.out.println("없음");
        }
    }
}
