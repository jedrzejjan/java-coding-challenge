package hello;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TranslationHelper {
    private static final String endpoint = "translation";
    private Translation translation;
    public TranslationHelper(Translation translation){
        this.translation = translation;
    }

    public synchronized TranslationRequest postTranslation(){

        String url = UnbabelConnection.mainPath + endpoint + UnbabelConnection.authParams;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(prepareBody(), headers);

        ResponseEntity<TranslationRequest> response = restTemplate.postForEntity(url, entity, TranslationRequest.class);
        System.out.println("@@@J translationRequest : " + response.getBody());
        System.out.println("@@@J response status : " + response.getStatusCode());

        if (response.getStatusCodeValue() == 201)
            return response.getBody();

        return null;
    }

    public synchronized TranslationRequest getTranslation(String uid) {

        String url = UnbabelConnection.mainPath+endpoint + "/" + uid + UnbabelConnection.authParams;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TranslationRequest> response = restTemplate.getForEntity(url, TranslationRequest.class);

        while (200 == response.getStatusCodeValue() && response.getBody().getTranslatedText() == null){
            response = restTemplate.getForEntity(url, TranslationRequest.class);
            if (200 != response.getStatusCodeValue()){
                return null;
            }
            try {
                Thread.sleep(2000);
            } catch (Exception e){}
        }
        notifyAll();
        return response.getBody();
    }

    private String prepareBody(){

        System.out.println("@@@J Enum en : " + LanguagesEnum.valueOf(translation.getLanguageFrom()).getShortcut());
        System.out.println("@@@J Enum en : " + LanguagesEnum.valueOf(translation.getLanguageTo()).getShortcut());
        String jsonObj = "{\"text\":\""+ translation.getTextToTranslate() + "\"," +
                "\"source_language\":\"" + LanguagesEnum.valueOf(translation.getLanguageFrom()).getShortcut() + "\"," +
                "\"target_language\":\"" + LanguagesEnum.valueOf(translation.getLanguageTo()).getShortcut() + "\"," +
                "\"text_format\":\"text\"}";
        return jsonObj;
    }
}
