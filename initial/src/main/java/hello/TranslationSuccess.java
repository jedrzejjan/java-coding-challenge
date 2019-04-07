package hello;

public class TranslationSuccess {

    private String fromLanguage;
    private String originalText;
    private String toLanguage;
    private String translatedText;
    private String status;

    public TranslationSuccess(String fromLanguage, String originalText, String toLanguage, String translatedText, String status) {
        this.fromLanguage = fromLanguage;
        this.originalText = originalText;
        this.toLanguage = toLanguage;
        this.translatedText = translatedText;
        this.status = status;
    }

    public String getFromLanguage() {
        return fromLanguage;
    }

    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getToLanguage() {
        return toLanguage;
    }

    public void setToLanguage(String toLanguage) {
        this.toLanguage = toLanguage;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
