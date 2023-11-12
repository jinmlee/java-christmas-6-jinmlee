package christmas.enums;

import java.util.List;

public enum Event {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", List.of(Day.CHRISTMAS), 100),
    DAY("평일 할인", List.of(Day.SUNDAY, Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY), 2023),
    WEEKEND("주말 할인", List.of(Day.FRIDAY, Day.SATURDAY), 2023),
    SPECIAL("특별 할인", List.of(Day.SUNDAY, Day.CHRISTMAS), 1000);

    private final String name;
    private final List<Day> days;
    private final int discount;

    Event(String name, List<Day> days, int discount) {
        this.name = name;
        this.days = days;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public List<Day> getDays() {
        return days;
    }

    public int getDiscount() {
        return discount;
    }
}
