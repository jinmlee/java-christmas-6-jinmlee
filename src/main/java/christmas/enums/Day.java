package christmas.enums;

public enum Day {
    FRIDAY(1),
    SATURDAY(2),
    SUNDAY(3),
    MONDAY(4),
    TUESDAY(5),
    WEDNESDAY(6),
    THURSDAY(7);

    private int dayOfWeek;

    Day(int dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }
}
