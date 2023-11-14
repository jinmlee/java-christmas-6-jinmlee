package christmas.enums;

public enum Day {
    FRIDAY(1),
    SATURDAY(2),
    SUNDAY(3),
    MONDAY(4),
    TUESDAY(5),
    WEDNESDAY(6),
    THURSDAY(7);

    private static final int WEEK = 7;
    private final int dayOfWeek;

    Day(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public static Day getVisitDayOfWeek(int date) {
        for (Day day : values()) {
            if (day.dayOfWeek == date % WEEK) {
                return day;
            }
        }
        return null;
    }
}
