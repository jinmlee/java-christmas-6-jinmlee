package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.Event;
import christmas.enums.Menu;
import christmas.model.Receipt;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDayEventTest {
    private ChristmasDayEvent christmasDayEvent;
    private Receipt receipt1;
    private Receipt receipt2;
    private Receipt receipt3;
    private Receipt receipt4;

    @BeforeEach
    void setUp() {
        christmasDayEvent = new ChristmasDayEvent();

        receipt1 = new Receipt(31, new HashMap<>() {{
            put(Menu.BARBECUE_RIBS, 2);
        }});

        receipt2 = new Receipt(1, new HashMap<>() {{
            put(Menu.BARBECUE_RIBS, 2);
        }});

        receipt3 = new Receipt(25, new HashMap<>() {{
            put(Menu.BARBECUE_RIBS, 2);
        }});

        receipt4 = new Receipt(26, new HashMap<>() {{
            put(Menu.BARBECUE_RIBS, 2);
        }});
    }

    @DisplayName("디데이 이벤트 적용 체크 테스트 코드, 방문날짜가 25일 이하일때 적용")
    @Test
    void checkApplyEvent() {
        assertThat(christmasDayEvent.checkApplyEvent(receipt1)).isFalse();
        assertThat(christmasDayEvent.checkApplyEvent(receipt2)).isTrue();
        assertThat(christmasDayEvent.checkApplyEvent(receipt3)).isTrue();
        assertThat(christmasDayEvent.checkApplyEvent(receipt4)).isFalse();
    }

    @DisplayName("등록된 날짜에 따라 디데이 할인이 잘 적용되는지 검사하는 테스트")
    @Test
    void getDiscount() {
        assertThat(christmasDayEvent.getDiscount(receipt2.getDate())).isEqualTo(1000);
        assertThat(christmasDayEvent.getDiscount(receipt3.getDate())).isEqualTo(3400);
    }

    @DisplayName("디데이 할인 혜택 내역이 잘 저장되는지 확인하는 테스트")
    @Test
    void applyEvent() {
        christmasDayEvent.applyEvent(receipt2);
        assertThat(receipt2.getBenefitsDetails().get(Event.CHRISTMAS_D_DAY)).isNotNull();
        christmasDayEvent.applyEvent(receipt3);
        assertThat(receipt3.getBenefitsDetails().get(Event.CHRISTMAS_D_DAY)).isNotNull();
    }
}