package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.InputCheck;

public class InputView {
    public static int readDate() {
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                return InputCheck.validateDate(Console.readLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
