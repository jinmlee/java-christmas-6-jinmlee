package christmas.util;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import christmas.enums.Menu;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class InputCheckTest {
    private InputCheck inputCheck;
    private HashMap<Menu, Integer> orderMenus;

    @BeforeEach
    void setUp() {
        inputCheck = new InputCheck();
        orderMenus = new HashMap<>();
        orderMenus.put(Menu.SEAFOOD_PASTA, 10);
        orderMenus.put(Menu.ZERO_COLA, 7);
        orderMenus.put(Menu.TAPAS, 4);
    }

    @DisplayName("올바른 숫자가 입력되지 않으면 예외처리")
    @ParameterizedTest
    @CsvSource({
            "0,true",
            "2147483647, true",
            "-2147483648, true",
            "a, false",
            "\\  10, false",
            "2147483648, false"
    })
    void checkInvalidNumber(String number, boolean result) {
        if (result == true) {
            assertThatCode(() -> inputCheck.checkInvalidNumber(number))
                    .doesNotThrowAnyException();
        }
        if (result == false) {
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> inputCheck.checkInvalidNumber(number));
        }
    }

    @DisplayName("입력한 날짜가 1부터 31 사이의 숫자가 아니면 예외처리")
    @ParameterizedTest
    @CsvSource({
            "1, true",
            "31, true",
            "20, true",
            "0, false",
            "32, false",
            "-1, false"
    })
    void checkDateRange(int date, boolean result) {
        if (result == true) {
            assertThatCode(() -> inputCheck.checkDateRange(date))
                    .doesNotThrowAnyException();
        }
        if (result == false) {
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> inputCheck.checkDateRange(date));
        }
    }

    @DisplayName("날짜 입력 예외처리 큰단위 테스트")
    @ParameterizedTest
    @CsvSource({
            "1, true",
            "31, true",
            "10, true",
            "32, false",
            "0, false",
            "ㅁㅁ, false",
            "a, false",
            "2 4, false",
            "\' \', false",
            "\'  10\',false"
    })
    void validateDate(String date, boolean result) {
        if (result == true) {
            assertThatCode(() -> inputCheck.validateDate(date))
                    .doesNotThrowAnyException();
        }
        if (result == false) {
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> inputCheck.validateDate(date));
        }
    }

    @DisplayName("주문 메뉴의 이름과 개수가 올바른 형식으로 입력되지 않으면 예외처리")
    @ParameterizedTest
    @CsvSource({
            "양송이수프-1, true",
            "타파스-10, true",
            "티본스테이그-4, true",
            "제로콜라:1, false",
            "바베큐립-1-1, false",
            "레드와인 -, false"
    })
    void checkMenuFormatValid(String orderMenu, boolean result) {
        if (result == true) {
            assertThatCode(() -> inputCheck.checkLineSeparator(orderMenu))
                    .doesNotThrowAnyException();
        }
        if (result == false) {
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> inputCheck.checkLineSeparator(orderMenu));
        }
    }

    @DisplayName("주문한 메뉴가 존재하지 않는 메뉴이면 예외처리")
    @ParameterizedTest
    @CsvSource({
            "양송이수프, true",
            "타파스, true",
            "바비큐립, true",
            "초코케이크, true",
            "파스타, false",
            "코카콜라, false",
            "신라면, false",
            "크리스마스 파스타, false"
    })
    void checkInvalidMenuName(String menuName, boolean result) {
        if (result == true) {
            assertThatCode(() -> inputCheck.checkInvalidMenuName(menuName))
                    .doesNotThrowAnyException();
        }
        if (result == false) {
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> inputCheck.checkInvalidMenuName(menuName));
        }
    }

    @DisplayName("주문한 메뉴에 중복된 메뉴가 있으면 예외처리")
    @ParameterizedTest
    @CsvSource({
            "YANGSONG_SOUP, true",
            "TBONE_STEAK, true",
            "BARBECUE_RIBS, true",
            "SEAFOOD_PASTA, false",
            "ZERO_COLA, false",
            "TAPAS, false"
    })
    void checkDuplicateName(String menuName, boolean result) {
        Menu menu = Menu.valueOf(menuName);
        if (result == true) {
            assertThatCode(() -> inputCheck.checkDuplicateName(menu, orderMenus))
                    .doesNotThrowAnyException();
        }
        if (result == false) {
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> inputCheck.checkDuplicateName(menu, orderMenus));
        }
    }

    @DisplayName("주문 목록에 음료만 있을시 예외처리")
    @Test
    void checkOnlyOrderBeverage() {
        HashMap<Menu, Integer> onlyBeverageMenu = new HashMap<>();
        onlyBeverageMenu.put(Menu.ZERO_COLA, 1);
        onlyBeverageMenu.put(Menu.CHAMPAGNE, 5);
        onlyBeverageMenu.put(Menu.RED_WINE, 4);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> inputCheck.checkOnlyOrderBeverage(onlyBeverageMenu));
    }

    @DisplayName("주문한 메뉴의 개수가 20개가 넘으면 예외처리")
    @Test
    void checkMenuCount() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> inputCheck.checkMenuCount(orderMenus));
    }

    @DisplayName("주문 메뉴의 이름과 개수 입력 예외처리 큰단위 테스트")
    @ParameterizedTest
    @CsvSource({
            "\'양송이수프-3,타파스-1,초코케이크-1,아이스크림-5\', true",
            "\'제로콜라-3,샴페인-3,시저샐러드-4,티본스테이크-2\', true",
            "\'아이스크림-5,해산물파스타-5,바비큐립-5,크리스마스파스타-5\', true",
            "\'바비큐립-1,해산물파스타-3,바비큐립-1\', false",
            "\'초코케이크,해산물파스타-3,바비큐립-1\', false",
            "\'바비큐립-10,초코케이크-11\', false",
            "\'양송이수프-0,제로콜라-10\', false",
            "\'바비큐립-1,초코케이크-1,\', false",
            "\'레드와인-1,제로콜라-5,샴페인-3\', false"
    })
    void validateOrder(String inputMenu, boolean result) {
        if (result == true) {
            assertThatCode(() -> inputCheck.validateOrder(inputMenu))
                    .doesNotThrowAnyException();
        }
        if (result == false) {
            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> inputCheck.validateOrder(inputMenu));
        }
    }
}