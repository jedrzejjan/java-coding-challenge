package hello;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.support.RequestHandledEvent;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Controller
public class TranslationController {

    List<TranslationSuccess> listOfTranslations = new ArrayList<TranslationSuccess>();

    @PostMapping("/translation")
    public String submitAndTranslate(@ModelAttribute Translation translation, Model model) {
        System.out.println("@@@J POST  ");

        model.addAttribute("langEnum", EnumSet.allOf(LanguagesEnum.class));
        model.addAttribute("languageFrom", UnbabelConnection.getLanguagesFrom());
        model.addAttribute("languageTo", UnbabelConnection.getLanguagesTo());

        TranslationRequest postTranslation = translation.postTranslation();

        System.out.println("@@@J postTranslation : " + postTranslation);
        if (postTranslation == null) return "translation";

        System.out.println("@@@J post uid : " + postTranslation.getUid());

        System.out.println("@@@J post status : " + postTranslation.getStatus());


        try {
            wait();
        } catch (Exception e){}

        TranslationRequest getTranslation = translation.getTranslation(postTranslation.getUid());
//        TranslationRequest getTranslation = translation.getTranslation("db5c5f21e9");
        if (getTranslation == null) return "translation";

        System.out.println("@@@J getTranslation : " + getTranslation);

        System.out.println("@@@J postTranslation : " + postTranslation);

        System.out.println("@@@J target_language : " + translation.getLanguageTo());
        System.out.println("@@@J source_language : " + translation.getLanguageFrom());

        model.addAttribute("newTranslation", getTranslation);
        listOfTranslations.add(new TranslationSuccess(
                getTranslation.getSource_language(),
                getTranslation.getText(),
                getTranslation.getTarget_language(),
                getTranslation.getTranslatedText(),
                getTranslation.getStatus()));

        model.addAttribute("rows", listOfTranslations);

        return "translation";
    }

    @GetMapping("/translation")
    public String translate(Model model) {
        System.out.println("@@@J GET  ");

        model.addAttribute("langEnum", EnumSet.allOf(LanguagesEnum.class));
        model.addAttribute("languageFrom", UnbabelConnection.getLanguagesFrom());
        model.addAttribute("languageTo", UnbabelConnection.getLanguagesTo());

        return "translation";
    }

    @EventListener
    public void handleEvent (RequestHandledEvent e) {
        System.out.println("-- RequestHandledEvent --");
        System.out.println(e);
        System.out.println(e.getDescription());
        System.out.println(e.getUserName());
    }
}
