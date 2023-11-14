package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.Event;
import christmas.enums.Menu;
import christmas.model.Receipt;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PresentEventTest {
    private PresentEvent presentEvent;
    private Receipt receipt1;
    private Receipt receipt2;
    private Receipt receipt3;
    private Receipt receipt4;

    @BeforeEach
    void setUp() {
        presentEvent = new PresentEvent();
        receipt1 = new Receipt(30, new HashMap<Menu, Integer>() {{
            put(Menu.ZERO_COLA, 1);
            put(Menu.YANGSONG_SOUP, 1);
            put(Menu.BARBECUE_RIBS, 2);
        }});

        receipt2 = new Receipt(1, new HashMap<Menu, Integer>() {{
            put(Menu.BARBECUE_RIBS, 2);
            put(Menu.TBONE_STEAK, 2);
        }});

        receipt3 = new Receipt(25, new HashMap<Menu, Integer>() {{
            put(Menu.CHOCO_CAKE, 2);
            put(Menu.ICECREAM, 2);
        }});

        receipt4 = new Receipt(31, new HashMap<Menu, Integer>() {{
            put(Menu.BARBECUE_RIBS, 2);
            put(Menu.CHOCO_CAKE, 2);
            put(Menu.TAPAS, 2);
        }});
    }

    @DisplayName("증정 이벤트 적용 체크 테스트, 토탈 주문 금액이 12만원 이상일 때 적용")
    @Test
    void checkApplyEvent() {
        assertThat(presentEvent.checkApplyEvent(receipt1)).isFalse();
        assertThat(presentEvent.checkApplyEvent(receipt2)).isTrue();
        assertThat(presentEvent.checkApplyEvent(receipt3)).isFalse();
        assertThat(presentEvent.checkApplyEvent(receipt4)).isTrue();
    }

    @DisplayName("적용된 증정 이벤트 내역이 잘 저장되는지 확인")
    @Test
    void applyEvent() {
        presentEvent.applyEvent(receipt2);
        assertThat(receipt2.getBenefitsDetails().get(Event.PRESENT)).isNotNull();
        presentEvent.applyEvent(receipt4);
        assertThat(receipt4.getBenefitsDetails().get(Event.PRESENT)).isNotNull();
    }
}