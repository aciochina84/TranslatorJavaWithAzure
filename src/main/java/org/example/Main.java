package org.example;

import com.azure.ai.translation.text.TextTranslationClient;
import com.azure.ai.translation.text.TextTranslationClientBuilder;
import com.azure.ai.translation.text.models.InputTextItem;
import com.azure.core.credential.AzureKeyCredential;


import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String TestoInput, TestoOutput, TargetLanguage  ;
        TargetLanguage = "en";
        TestoInput="ciao amici tutto bene";
        String apiKey = "802fcc772221463c95e7c5972530ed1c";
        String region = "switzerlandnorth";
        AzureKeyCredential credential = new AzureKeyCredential(apiKey);

        // creo il client per la traduzione
        TextTranslationClient myTextTranslationClient =
                new TextTranslationClientBuilder()
                        .credential(credential)
                        .region(region)
                        .buildClient();

        // creo la lista delle lingue target
        List<String> myListTargetLanguages = new ArrayList<>();
        // aggiungo alla lista il TargetLanguage (en)
        myListTargetLanguages.add(TargetLanguage);

        // creo la lista dei testi da tradurre
        List<InputTextItem> myListTestiDaTradurre = new ArrayList<>();
        // aggiungo alla lista il testo da tradurre
        myListTestiDaTradurre.add(new InputTextItem(TestoInput));

        // chiamo il servizio Azure e ottengo il response
        var response = myTextTranslationClient.translate(myListTargetLanguages,myListTestiDaTradurre);

        var myResponse = response.iterator().next();

        var translations = myResponse.getTranslations();

        for (var translation : translations) {
            System.out.println("Textul tradus: " + translation.getText());
        }

    }
}