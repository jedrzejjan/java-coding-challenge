package hello;

public class Translation {
    private static final String endpoint = "translation";
    private String textToTranslate;
    private String languageFrom;
    private String languageTo;
    private int status;
    private TranslationHelper translationHelper;

    public Translation(){
        this.translationHelper = new TranslationHelper(this);
    }

    public Translation(String textToTranslate, String languageFrom, String languageTo) {
        this.textToTranslate = textToTranslate;
        this.languageFrom = languageFrom;
        this.languageTo = languageTo;
        this.translationHelper = new TranslationHelper(this);
    }

    public Translation(String textToTranslate, String languageFrom, String languageTo, int status) {
        this.textToTranslate = textToTranslate;
        this.languageFrom = languageFrom;
        this.languageTo = languageTo;
        this.status = status;
        this.translationHelper = new TranslationHelper(this);
    }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public String getTextToTranslate() {
        return textToTranslate;
    }

    public void setTextToTranslate(String textToTranslate) {
        this.textToTranslate = textToTranslate;
    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public void setLanguageFrom(String languageFrom) {
        this.languageFrom = languageFrom;
    }

    public String getLanguageTo() {
        return languageTo;
    }

    public void setLanguageTo(String languageTo) {
        this.languageTo = languageTo;
    }


    public synchronized TranslationRequest postTranslation(){
        return translationHelper.postTranslation();
    }

    public synchronized TranslationRequest getTranslation(String uid) {
        return translationHelper.getTranslation(uid);
    }

}
