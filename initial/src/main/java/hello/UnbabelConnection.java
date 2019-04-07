package hello;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

public class UnbabelConnection {

    private static final String api_key = "9db71b322d43a6ac0f681784ebdcc6409bb83359";
    private static final String username = "fullstack-challenge";
    public static final String mainPath = "https://sandbox.unbabel.com/tapi/v2/";
    public static final String authParams = "?api_key=" + api_key + "&username=" + username;

    private static Set<String> languagesFrom = new HashSet<>();
    private static Set<String> languagesTo = new HashSet<>();

    public static void getLanguagePairs(){
        final String endpoint = "language_pair";
        final String uri = mainPath + endpoint + authParams;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        JSONObject jo = new JSONObject(result);
        JSONArray langPairs = jo.getJSONArray("objects");

        for (int i = 0; i < langPairs.length(); i++) {
            JSONObject jsonObject = langPairs.getJSONObject(i);
            languagesFrom.add(jsonObject.getJSONObject("lang_pair").getJSONObject("source_language").getString("name"));
            languagesTo.add(jsonObject.getJSONObject("lang_pair").getJSONObject("target_language").getString("name"));
        }
    }

    public static Set<String> getLanguagesFrom(){
        return languagesFrom;
    }

    public static Set<String> getLanguagesTo(){
        return languagesTo;
    }

}
