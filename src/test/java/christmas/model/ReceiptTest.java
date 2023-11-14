package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.Badge;
import christmas.enums.Day;
import christmas.enums.Event;
import christmas.enums.Menu;
import christmas.event.ChristmasDayEvent;
import christmas.event.WeekdayEvent;
import christmas.event.WeekendEvent;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReceiptTest {
    private Receipt receipt1;
    private Receipt receipt2;
    private Receipt receipt3;
    private Receipt receipt4;
    private ChristmasDayEvent christmasDayEvent = new ChristmasDayEvent();
    private WeekdayEvent weekdayEvent = new WeekdayEvent();
    private WeekendEvent weekendEvent = new WeekendEvent();

    @BeforeEach
    void setUp() {
        receipt1 = new Receipt(30, new HashMap<>() {{
            put(Menu.ZERO_COLA, 1);
            put(Menu.YANGSONG_SOUP, 1);
            put(Menu.BARBECUE_RIBS, 2);
        }});
        receipt1.getBenefitsDetails().putAll(new HashMap<>() {{
            put(Event.WEEKEND, weekendEvent.getDiscount(receipt1));
        }});

        receipt2 = new Receipt(1, new HashMap<>() {{
            put(Menu.BARBECUE_RIBS, 2);
            put(Menu.TBONE_STEAK, 2);
        }});
        receipt2.getBenefitsDetails().putAll(new HashMap<>() {{
            put(Event.WEEKEND, weekendEvent.getDiscount(receipt2));
            put(Event.PRESENT, Menu.CHAMPAGNE.getPrice());
            put(Event.CHRISTMAS_D_DAY, christmasDayEvent.getDiscount(receipt2.getDate()));
        }});

        receipt3 = new Receipt(25, new HashMap<>() {{
            put(Menu.CHOCO_CAKE, 2);
            put(Menu.ICECREAM, 2);
        }});
        receipt3.getBenefitsDetails().putAll(new HashMap<>() {{
            put(Event.SPECIAL, 1000);
            put(Event.WEEKDAY, weekdayEvent.getDiscount(receipt3));
            put(Event.CHRISTMAS_D_DAY, christmasDayEvent.getDiscount(receipt3.getDate()));
        }});

        receipt4 = new Receipt(31, new HashMap<>() {{
            put(Menu.BARBECUE_RIBS, 2);
            put(Menu.CHOCO_CAKE, 2);
            put(Menu.TAPAS, 2);
        }});
        receipt4.getBenefitsDetails().putAll(new HashMap<>() {{
            put(Event.SPECIAL, 1000);
            put(Event.WEEKDAY, weekdayEvent.getDiscount(receipt4));
            put(Event.PRESENT, Menu.CHAMPAGNE.getPrice());
        }});
    }

    @DisplayName("할인전 토탈 금액을 계산하는 테스트")
    @Test
    void getTotalPrice() {
        assertThat(receipt1.getTotalPrice()).isEqualTo(117000);
        assertThat(receipt2.getTotalPrice()).isEqualTo(218000);
        assertThat(receipt3.getTotalPrice()).isEqualTo(40000);
        assertThat(receipt4.getTotalPrice()).isEqualTo(149000);
    }

    @DisplayName("방문 날짜가 무슨요일인지 계산하는 테스트")
    @Test
    void getDayOfWeek() {
        assertThat(receipt1.getDayOfWeek()).isEqualTo(Day.SATURDAY);
        assertThat(receipt2.getDayOfWeek()).isEqualTo(Day.FRIDAY);
        assertThat(receipt3.getDayOfWeek()).isEqualTo(Day.MONDAY);
        assertThat(receipt4.getDayOfWeek()).isEqualTo(Day.SUNDAY);
    }

    @DisplayName("총 혜택 금액이 얼마인지 계산하는 테스트, 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격")
    @Test
    void getTotalBenefitAmount() {
        assertThat(receipt1.getTotalBenefitAmount()).isEqualTo(4046);
        assertThat(receipt2.getTotalBenefitAmount()).isEqualTo(8092 + 25000 + 1000);
        assertThat(receipt3.getTotalBenefitAmount()).isEqualTo(1000 + 8092 + 3400);
        assertThat(receipt4.getTotalBenefitAmount()).isEqualTo(1000 + 4046 + 25000);
    }

    @DisplayName("예상 결제 금액을 계산하는 테스트, 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액")
    @Test
    void getFinalTotalPrice() {
        assertThat(receipt1.getFinalTotalPrice()).isEqualTo(117000 - 4046);
        assertThat(receipt2.getFinalTotalPrice()).isEqualTo(218000 - 9092);
        assertThat(receipt3.getFinalTotalPrice()).isEqualTo(40000 - 12492);
        assertThat(receipt4.getFinalTotalPrice()).isEqualTo(149000 - 5046);
    }

    @DisplayName("배지를 증정하는 테스트, 총 혜택금액이 2만원 이상: 산타, 만원 이상 : 트리, 오천원 이상 : 별, 그 외 없음")
    @Test
    void getBadge() {
        assertThat(receipt1.getBadge()).isEqualTo(Badge.NOTHING);
        assertThat(receipt2.getBadge()).isEqualTo(Badge.SANTA);
        assertThat(receipt3.getBadge()).isEqualTo(Badge.TREE);
        assertThat(receipt4.getBadge()).isEqualTo(Badge.SANTA);
    }
}