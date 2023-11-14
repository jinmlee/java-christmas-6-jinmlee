package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.Event;
import christmas.enums.Menu;
import christmas.model.Receipt;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialEventTest {
    private SpecialEvent specialEvent;
    private Receipt receipt1;
    private Receipt receipt2;
    private Receipt receipt3;
    private Receipt receipt4;

    @BeforeEach
    void setUp() {
        specialEvent = new SpecialEvent();
        receipt1 = new Receipt(1, new HashMap<Menu, Integer>() {{
            put(Menu.BARBECUE_RIBS, 2);
        }});

        receipt2 = new Receipt(4, new HashMap<Menu, Integer>() {{
            put(Menu.BARBECUE_RIBS, 2);
        }});

        receipt3 = new Receipt(25, new HashMap<Menu, Integer>() {{
            put(Menu.BARBECUE_RIBS, 2);
        }});

        receipt4 = new Receipt(31, new HashMap<Menu, Integer>() {{
            put(Menu.BARBECUE_RIBS, 2);
        }});
    }

    @DisplayName("스페셜 할인 적용 체크 테스트, 방문날짜가 달력에 별이 찍힌 날과 같을 때 적용")
    @Test
    void checkApplyEvent() {
        assertThat(specialEvent.checkApplyEvent(receipt1)).isFalse();
        assertThat(specialEvent.checkApplyEvent(receipt2)).isFalse();
        assertThat(specialEvent.checkApplyEvent(receipt3)).isTrue();
        assertThat(specialEvent.checkApplyEvent(receipt4)).isTrue();
    }

    @Test
    void applyEvent() {
        specialEvent.applyEvent(receipt3);
        assertThat(receipt3.getBenefitsDetails().get(Event.SPECIAL)).isNotNull();
        specialEvent.applyEvent(receipt4);
        assertThat(receipt4.getBenefitsDetails().get(Event.SPECIAL)).isNotNull();
    }
}