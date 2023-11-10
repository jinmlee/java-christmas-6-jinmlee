package christmas.view;

import java.util.HashMap;

public class OutputView {
    public void printOrderMenu(HashMap<String, Integer> orderMenus) {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println("<주문 메뉴>");
        for (String menuName : orderMenus.keySet()) {
            System.out.println(menuName + " " + orderMenus.get(menuName) + "개");
        }
    }
}
