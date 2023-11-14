package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.Event;
import christmas.enums.Menu;
import christmas.model.Receipt;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayEventTest {
    private WeekdayEvent weekdayEvent;
    private Receipt receipt1;
    private Receipt receipt2;
    private Receipt receipt3;
    private Receipt receipt4;

    @BeforeEach
    void setUp() {
        weekdayEvent = new WeekdayEvent();
        receipt1 = new Receipt(30, new HashMap<>() {{
            put(Menu.ZERO_COLA, 1);
            put(Menu.YANGSONG_SOUP, 1);
            put(Menu.BARBECUE_RIBS, 2);
        }});

        receipt2 = new Receipt(1, new HashMap<>() {{
            put(Menu.BARBECUE_RIBS, 2);
            put(Menu.TBONE_STEAK, 2);
        }});

        receipt3 = new Receipt(25, new HashMap<>() {{
            put(Menu.CHOCO_CAKE, 2);
            put(Menu.ICECREAM, 2);
        }});

        receipt4 = new Receipt(31, new HashMap<>() {{
            put(Menu.BARBECUE_RIBS, 2);
            put(Menu.CHOCO_CAKE, 2);
            put(Menu.TAPAS, 2);
        }});
    }

    @DisplayName("평일 할인 적용 체크 테스트코드, 방문날짜가 일,월,화,수,목요일에 포함될 때 적용")
    @Test
    void checkApplyEvent() {
        assertThat(weekdayEvent.checkApplyEvent(receipt1)).isFalse();
        assertThat(weekdayEvent.checkApplyEvent(receipt2)).isFalse();
        assertThat(weekdayEvent.checkApplyEvent(receipt3)).isTrue();
        assertThat(weekdayEvent.checkApplyEvent(receipt4)).isTrue();
    }

    @DisplayName("평일할인 디저트 메뉴만 할인 적용되는지 테스트")
    @Test
    void getDiscount() {
        assertThat(weekdayEvent.getDiscount(receipt3)).isEqualTo(8092);
        assertThat(weekdayEvent.getDiscount(receipt4)).isEqualTo(4046);
    }

    @DisplayName("적용된 평일 할인 내역이 잘 저장되는지 테스트")
    @Test
    void applyEvent() {
        weekdayEvent.applyEvent(receipt3);
        assertThat(receipt3.getBenefitsDetails().get(Event.WEEKDAY)).isNotNull();
        weekdayEvent.applyEvent(receipt4);
        assertThat(receipt4.getBenefitsDetails().get(Event.WEEKDAY)).isNotNull();
    }
}