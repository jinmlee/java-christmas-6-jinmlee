package christmas.enums;

import java.util.List;

public enum Discount {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", List.of(25) , 100),
    DAY("평일 할인", List.of(3, 4, 5, 6, 7), 2023),
    WEEKEND("주말 할인", List.of(1, 2), 2023),
    SPECIAL("특별 할인", List.of(3, 10, 17, 24, 25, 31), 1000);

    private final String name;
    private final List<Integer> days;
    private final int price;

    Discount(String name, List<Integer> days, int price) {
        this.name = name;
        this.days = days;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public List<Integer> getDays(){
        return days;
    }

    public int getPrice(){
        return price;
    }
}
