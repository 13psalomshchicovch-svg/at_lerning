package less14.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import less14.pages.component.DatePicker;
import less14.pages.component.RegistrationTable;


import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private DatePicker datePicker = new DatePicker();
    private SelenideElement firstName=  $("#firstName");
    //private String titleText = "Srudent Registration Form";

    public RegistrationPage openPage() {
//        open("https://demoqa.com/automation-practice-form");
//        executeJavaScript("$('#fixedban'.remove())");
//        executeJavaScript("$('footer'.remove())");
        open("https://demoqa.com/");
        $(".category-cards").$("a",1).click();
        //$(".row").shouldHave(text("Please select an item from left to start practice."));
        $$(".accordion div.element-group").get(1).$("a").click();
        Configuration.timeout = 50000;

        return this;
    }

    public RegistrationPage setFirst(String value){
        firstName.setValue(value);

        return this;
    }

    public RegistrationPage setLast(String value){
        $("#lastName").setValue(value);

        return this;

    }

    public RegistrationPage setEmail(String value){
        $("#userEmail").setValue(value);

        return this;
    }

    public RegistrationPage selectGender(String value){
        $("#genterWrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationPage setPhnumber(String value){
        $("#userNumber").setValue(value);

        return this;
    }

    public RegistrationPage setBirth(String year, String month, String day){
        $("#dateOfBirthInput").click();
        datePicker.setDate(year, month, day);

        return this;
    }

    public RegistrationPage selectSubjects(String value){
        $("#subjectsInput").setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage selectHobbies(String value){
        $("#hobbiesWrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploadPick(){
        File pics = new File("C:/Users/13ko31/Pictures/pics.jpg"); // tru creat some more usefull
        $("#uploadPicture").uploadFile(pics);

        return this;
    }

    public RegistrationPage setAddress(String value){
        $("#currentAddress").setValue(value);

        return this;
    }

    public RegistrationPage setState(String value){
        $("#react-select-3-input").setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setCity(String value){
        $("#react-select-4-input").setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage verifyTableOpen(){
        new RegistrationTable().verifyTableOpen();

        return this;
    }

    public RegistrationPage verifyData(String key, String value){
        new RegistrationTable().verifyCorrectedData(key, value);

        return this;
    }

    public RegistrationPage endRegistration(){
        $("#submit").pressEnter();

        return this;
    }
}
