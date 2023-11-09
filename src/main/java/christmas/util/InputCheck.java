package christmas.util;

public class InputCheck {
    private static final int minDate = 1;
    private static final int maxDate = 31;

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
        if(date < minDate || date > maxDate){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
