package christmas.util;

import christmas.enums.Menu;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InputCheck {
    private static final int one = 1;
    private static final int maxDate = 31;
    private static final int maxOrderCount = 20;
    private static final int nameIndex = 0;
    private static final int countIndex = 1;

    public static int validateDate(String input) {
        int date = checkInvalidNumber(input);
        checkDateRange(date);
        return date;
    }

    public static int checkInvalidNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkDateRange(int date) {
        if (date < one || date > maxDate) {
            throw new IllegalArgumentException();
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
            String menuName = checkInvalidMenuName(nameAndCount.get(nameIndex));
            int menuCount = checkInvalidNumber(nameAndCount.get(countIndex));
            checkDuplicateName(menuName, orderMenus);
            orderMenus.put(menuName, menuCount);
        }
    }

    public static List<String> checkMenuFormatValid(String inputMenu) {
        List<String> nameAndCount = Arrays.asList(inputMenu.split("-"));
        if (nameAndCount.size() != 2) {
            throw new IllegalArgumentException();
        }
        return nameAndCount;
    }

    public static String checkInvalidMenuName(String orderMenuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(orderMenuName)) {
                return orderMenuName;
            }
        }
        throw new IllegalArgumentException();
    }

    public static void checkDuplicateName(String menuName, HashMap<String, Integer> orderMenus) {
        if (orderMenus.containsKey(menuName)) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkMenuCount(HashMap<String, Integer> orderMenus) {
        int orderCount = 0;
        for (int menuCount : orderMenus.values()) {
            orderCount += menuCount;
        }
        if (orderCount > maxOrderCount || orderCount < one) {
            throw new IllegalArgumentException();
        }
    }
}
