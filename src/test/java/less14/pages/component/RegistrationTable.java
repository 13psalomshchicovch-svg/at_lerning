package less14.pages.component;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationTable {
    public void verifyTableOpen(){
        $(".modal-dialog").should(Condition.appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }

    public void verifyCorrectedData(String key, String value){
        $(".table-responsive").$(byText(key)).parent().
                shouldHave(text(value));

    }
}
