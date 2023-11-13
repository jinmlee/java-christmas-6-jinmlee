package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.enums.Event;
import christmas.enums.Menu;
import christmas.model.Receipt;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendEventTest {
    private WeekendEvent weekendEvent;
    private Receipt receipt1;
    private Receipt receipt2;
    private Receipt receipt3;
    private Receipt receipt4;

    @BeforeEach
    void setUp() {
        weekendEvent = new WeekendEvent();
        receipt1 = new Receipt(10, new HashMap<Menu, Integer>() {{
            put(Menu.ZERO_COLA, 1);
            put(Menu.YANGSONG_SOUP, 1);
        }});

        receipt2 = new Receipt(1, new HashMap<Menu, Integer>() {{
            put(Menu.BARBECUE_RIBS, 2);
            put(Menu.TBONE_STEAK, 2);
        }});

        receipt3 = new Receipt(25, new HashMap<Menu, Integer>() {{
            put(Menu.CHOCO_CAKE, 2);
            put(Menu.ICECREAM, 2);
        }});

        receipt4 = new Receipt(30, new HashMap<Menu, Integer>() {{
            put(Menu.BARBECUE_RIBS, 2);
            put(Menu.CHOCO_CAKE, 2);
            put(Menu.TAPAS, 2);
        }});
    }

    @DisplayName("주말할인 적용 체크 테스트 코드, 방문날짜가 금,토요일이 포함되고, 주문금액이 10000원이 넘을때 적용")
    @Test
    void checkApplyEvent() {
        assertThat(weekendEvent.checkApplyEvent(receipt1)).isFalse();
        assertThat(weekendEvent.checkApplyEvent(receipt2)).isTrue();
        assertThat(weekendEvent.checkApplyEvent(receipt3)).isFalse();
        assertThat(weekendEvent.checkApplyEvent(receipt4)).isTrue();
    }

    @DisplayName("주말할인이 메인메뉴에만 할인이 잘 적용되는지 테스트")
    @Test
    void getDiscount() {
        assertThat(weekendEvent.getDiscount(receipt2)).isEqualTo(8092);
        assertThat(weekendEvent.getDiscount(receipt4)).isEqualTo(4046);
    }

    @DisplayName("적용된 주말할인 내역이 잘 저장되는지 확인하는 테스트")
    @Test
    void applyEvent() {
        weekendEvent.applyEvent(receipt2);
        assertThat(receipt2.getBenefitsDetails().get(Event.WEEKEND)).isNotNull();
        weekendEvent.applyEvent(receipt4);
        assertThat(receipt4.getBenefitsDetails().get(Event.WEEKEND)).isNotNull();
    }
}