package christmas.util;

import christmas.enums.Menu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InputCheck {
    private static final int minDate = 1;
    private static final int maxDate = 31;
    private static final int maxOrderCount = 20;

    public static int validateDate(String input) {
        int date = checkInvalidNumber(input);
        checkDateRange(date);
        return date;
    }

    public static int checkInvalidNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 숫자입니다. 다시 입력해 주세요.");
        }
    }

    public static void checkDateRange(int date) {
        if (date < minDate || date > maxDate) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static HashMap<String, Integer> validateOrder(String input) {
        HashMap<String, Integer> orderMenus = new HashMap<>();
        List<String> inputMenus = Arrays.asList(input.split(","));
        checkOrderMenus(inputMenus, orderMenus);
        checkMenuCount(orderMenus);
        return orderMenus;
    }

    public static void checkOrderMenus(List<String> inputMenus, HashMap<String, Integer> orderMenus) {
        for (String menu : inputMenus) {
            List<String> nameAndCount = checkMenuFormatValid(menu);
            String menuName = checkInvalidMenu(nameAndCount.get(0));
            int menuCount = checkInvalidNumber(nameAndCount.get(1));
            checkDuplicateName(menuName, orderMenus);
            orderMenus.put(menuName, menuCount);
        }
    }

    public static List<String> checkMenuFormatValid(String inputMenu) {
        List<String> nameAndCount = Arrays.asList(inputMenu.split("-"));
        if (nameAndCount.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        return nameAndCount;
    }

    public static String checkInvalidMenu(String orderMenuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(orderMenuName)) {
                return orderMenuName;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public static void checkDuplicateName(String menuName, HashMap<String, Integer> orderMenus) {
        if (orderMenus.containsKey(menuName)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void checkMenuCount(HashMap<String, Integer> orderMenus) {
        int orderCount = 0;
        for (int menuCount : orderMenus.values()) {
            orderCount += menuCount;
        }
        if (orderCount > maxOrderCount) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
