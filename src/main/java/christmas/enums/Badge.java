package christmas.enums;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NOTHING("없음", 0);

    private final String name;
    private final int minBenefitAmount;

    Badge(String name, int minBenefitAmount) {
        this.name = name;
        this.minBenefitAmount = minBenefitAmount;
    }

    public String getName() {
        return name;
    }

    public int getMinBenefitAmount() {
        return minBenefitAmount;
    }
}
