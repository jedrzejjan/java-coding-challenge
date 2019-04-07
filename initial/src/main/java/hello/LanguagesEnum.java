package hello;

public enum LanguagesEnum {
    English("en"),
    Portuguese("pt"),
    German("gr"),
    Italian("it"),
    Russian("ru"),
    Japanese("ja"),
    Turkish("tr"),
    Spanish_Latam("es-latam"),
    French("fr"),
    Arabic("ar"),
    Chinese_Simplified("zh-CN"),
    Spanish("es"),
    Hindi("hi"),
    Dutch("nl");

    private String shortcut;

    LanguagesEnum(String shortcut){
        this.shortcut = shortcut;
    }

    public String getShortcut(){
        return shortcut;
    }
}
