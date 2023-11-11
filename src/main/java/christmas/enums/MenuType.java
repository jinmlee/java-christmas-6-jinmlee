package christmas.enums;

public enum MenuType {
    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료");

    private String type;

    MenuType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
