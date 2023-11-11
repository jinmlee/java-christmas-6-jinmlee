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

    public static HashMap<Menu, Integer> validateOrder(String input) {
        HashMap<Menu, Integer> orderMenus = new HashMap<>();
        List<String> inputMenus = Arrays.asList(input.split(","));
        checkLastComma(input);
        checkOrderMenus(inputMenus, orderMenus);
        checkMenuCount(orderMenus);
        checkOnlyOrderBeverage(orderMenus);
        return orderMenus;
    }

    public static void checkOrderMenus(List<String> inputMenus, HashMap<Menu, Integer> orderMenus) {
        for (String menu : inputMenus) {
            List<String> nameAndCount = checkLineSeparator(menu);
            Menu menuName = checkInvalidMenuName(nameAndCount.get(nameIndex));
            int menuCount = checkInvalidNumber(nameAndCount.get(countIndex));
            checkDuplicateName(menuName, orderMenus);
            orderMenus.put(menuName, menuCount);
        }
    }

    public static List<String> checkLineSeparator(String inputMenu) {
        List<String> nameAndCount = Arrays.asList(inputMenu.split("-"));
        if (nameAndCount.size() != 2) {
            throw new IllegalArgumentException();
        }
        return nameAndCount;
    }

    public static Menu checkInvalidMenuName(String orderMenuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(orderMenuName)) {
                return menu;
            }
        }
        throw new IllegalArgumentException();
    }

    public static void checkDuplicateName(Menu menu, HashMap<Menu, Integer> orderMenus) {
        if (orderMenus.containsKey(menu)) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkMenuCount(HashMap<Menu, Integer> orderMenus) {
        int totalCount = 0;
        for (int menuCount : orderMenus.values()) {
            checkRangeMenuCount(menuCount);
            totalCount += menuCount;
        }
        checkRangeMenuCount(totalCount);
    }

    public static void checkRangeMenuCount(int menuCount) {
        if (menuCount > maxOrderCount || menuCount < one) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkLastComma(String input) {
        boolean endsWithComma = input.endsWith(",");
        if (endsWithComma) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkOnlyOrderBeverage(HashMap<Menu, Integer> orderMenus){
        for(Menu menu : orderMenus.keySet()){
            if(!menu.getType().equals("BEVERAGE")){
                return;
            }
        }
        throw new IllegalArgumentException();
    }
}
